package com.databaker.hyzx.controller;

import com.databaker.hyzx.client.RealTimeAsrClient;
import com.databaker.hyzx.entity.MeetingEntity;
import com.databaker.hyzx.speech.*;
import com.databaker.hyzx.utils.HttpUtils;
import com.google.gson.*;
import com.googlecode.protobuf.format.JsonFormat;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.databaker.hyzx.constant.Constants.*;
import static com.databaker.hyzx.utils.ConvertJsonUtils.convertToDataBakerJson;

/**
 *
 */
@Component
@Data
public class RealTimeAsr implements ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(RealTimeAsr.class);

    private  RealTimeAsrClient client;
    // 采样率
    int sampleRate = 16000;
    // 声道数
    int numChannel = 1;

    private AtomicInteger index = new AtomicInteger(0);

    private Gson gson = new Gson();

    private String roomId;

    private CopyOnWriteArraySet<WebSocketServer> set = null;

    private String hotWordId ;

    private String hotWordUrl;

    private static ApplicationContext ac;

    public RealTimeAsr(){

    }

    public RealTimeAsr(String roomId,String hotWordId,String hotWordUrl ) {
        this.hotWordUrl = hotWordUrl;
        this.roomId = roomId;
        this.client = (RealTimeAsrClient)ac.getBean("realTimeAsrClient");
        this.client.setRoomId(roomId);
        StreamingSpeechRequest.Builder requestBuilder = setAudioConfig(hotWordId);
        // 建立链接
        this.client.buildConnection(requestBuilder);
    }

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public void realTimeTransform(){
        if(Integer.valueOf(index.toString())<1){
            index.incrementAndGet();
            realTimeTrans();
        }
    }

    public void realTimeTrans() {
        Runnable sentThread = () -> {
        try {
            for(;;){
                MeetingEntity meetingEntity = MEETING_ENTITY_MAP.get(this.roomId);
                if(meetingEntity==null || meetingEntity.getIsFinal()==1 || meetingEntity.getIsFinal()==2){//会议不存在，结束，删除则终止发包
                    break;
                }
                    LinkedBlockingQueue<byte[]> queue = meetingEntity.getQueue();
                    byte[] buf = queue.poll();
                    if(buf == null){
                       buf = new byte[0];
                    }
                    //logger.info("发送数据包roomId={}",roomId);
                    client.postAudio(buf);
                    Thread.sleep(150);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        };

        // 线程2主要用于读取转写结果
        Runnable readThread = () -> {
            MeetingEntity meetingEntity = MEETING_ENTITY_MAP.get(roomId);
            // logger.info("readThread启动");
            // 获取转写结果
            LinkedBlockingQueue<StreamingSpeechResponse> responses = client.getResult();
            try {
                // 等待连接成功，输出转写全局ID
                while (responses.size() == 0) {
                    Thread.sleep(1);
                }
                //logger.info("转写全局ID={}" , client.getGlobalStreamId());
                // 判断是否转写结束且都已将全部转写结果读出
                while (!client.isFinished()) {
                    while (responses.size() != 0) {
                        StreamingSpeechResponse response = responses.poll();

                        try {
                            if(StringUtils.isEmpty(String.valueOf(response.getResult()))){
                                continue;
                            }
                        }catch (Exception exception){
                            continue;
                        }

                        String result = JsonFormat.printToString(response.getResult());
                        logger.info("转写结果roomid = {},{}:" ,roomId,result);
                        String dataBakerJson = convertToDataBakerJson(result,client.getGlobalStreamId());
                        if(StringUtils.isEmpty(dataBakerJson)){
                            continue;
                        }
                        Map messageMap = gson.fromJson(dataBakerJson,Map.class);
                        String text = messageMap.get("asr_text").toString();
                        if(( text.equals(meetingEntity.getBeforeText()) && STATEMENTONE.contains(text))){
                            continue;
                        }
                        for (WebSocketServer se :set){
                            //logger.info("dataBakerJson转换后=  {}",dataBakerJson);
                            se.getSession().getAsyncRemote().sendText(dataBakerJson);
                        }
                        Map json = gson.fromJson(dataBakerJson,Map.class);
                        String sentence_end = json.get("sentence_end").toString();
                        if(sentence_end.equals("true")){
                            meetingEntity.buildData(json,client.getGlobalStreamId());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            client.shutdownChannel();
            if(meetingEntity.getIsFinal()==1){
                meetingEntity.savData();
                MEETING_ENTITY_MAP.remove(roomId);
                for (WebSocketServer se :set){
                    try {
                        se.getSession().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        ExecutorService sentThreadPool = newFixedThreadPool(3);
        //ExecutorService readThreadPool = newFixedThreadPool(1);
        sentThreadPool.execute(sentThread);
        sentThreadPool.execute(readThread);
        sentThreadPool.shutdown();
        //readThreadPool.shutdown();
    }

    /**
     * 音频转写相关设置
     */
    public StreamingSpeechRequest.Builder setAudioConfig(String hotWordId) {
        // 指定规则替换文本。
        WordsReplace wordsReplace = WordsReplace.newBuilder()
                // 待替换的文本。最多支持100个词
                .addKeywords("")
                // 替换后的字符。不指定时替换为空。最多支持100个符号，和待替换文本一一对应。
                .addReplace("")
                .build();

        // 音频相关设置。
        AudioConfig audioConfig = AudioConfig.newBuilder()
                // 音频的编码。
                .setAue(AudioConfig.AudioEncoding.PCM)
                // 采样率（范围为8000-48000）。（不一定支持，目前工程没有做转码。）
                .setSampleRate(sampleRate)
                .build();

        // 关键词识别。转写结果中返回同音词。（支持词库总数不超过30个。支持2-4个词）*
        List<String> keywords = new ArrayList<>();
        if(!StringUtils.isEmpty(hotWordId)){
            Map<String ,String> pram = new HashMap<String,String>();
            pram.put("id",hotWordId);
            logger.info("热词接口hotWordUrl{},roomid={}",hotWordUrl,roomId);
            String str = HttpUtils.sendPost(hotWordUrl,pram);
            JsonElement je = new JsonParser().parse(str);
            logger.info("热词接口返回{},roomid={}",str,roomId);
            JsonPrimitive codeObj =  je.getAsJsonObject().getAsJsonPrimitive("code");
            if(codeObj!=null){
                String code = codeObj.toString();
                if("200".equals(code)){
                    JsonArray jsonArray = null;
                    try {
                        jsonArray = je.getAsJsonObject().getAsJsonArray("data");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if(jsonArray!=null && jsonArray.size()>0){
                        for (int i = 0;i < jsonArray.size();i++){
                            String terms = jsonArray.get(i).getAsJsonObject().get("terms").toString();
                            terms = terms.substring(1,terms.length()-1);
                            keywords.add(terms);
                            logger.info("热词{},roomid={}",terms,roomId);
                        }
                    }
                }
            }
        }
        // 识别相关设置。
        SpeechConfig speechConfig = SpeechConfig.newBuilder()
                // 转写的语言。
                .setLang(SpeechConfig.Language.MANDARIN)
                // 情景模式，针对不同的应用场景可定制模型，例如医疗。
                .setScene(SpeechConfig.Scene.GENERALSCENE)
                // 关键词识别。转写结果中返回同音词。（支持词库总数不超过30个。支持2-4个词）*
                .addAllKeyWords(keywords)
                // 自定义词语（支持中文2-4个字，中英混合4-8个字符）
                .addCustomWord("")
                // 识别类型（全部，仅逐句, 仅逐字）
                //   SpeechConfig.RecognizeType.UTTERANCE: 逐句模式
                //   SpeechConfig.RecognizeType.STREAMING: 逐字模式
                //   SpeechConfig.RecognizeType.ALL: 全部
                .setRecognizeType(SpeechConfig.RecognizeType.ALL)
                // 统一数字的转换方式。默认false，开启阿拉伯数字能力。true为汉字一二三四五六七八九十。
                .setDisableConvertNumber(false)
                // 加标点。默认false，开启添加标点。
                .setDisablePunctuation(false)
                // 指定规则替换文本。
                .setWordsReplace(wordsReplace)
                .build();

        // 音频流请求的相关设置。
        StreamingSpeechConfig streamingSpeechConfig = StreamingSpeechConfig.newBuilder()
                // 音频设置。
                .setAudioConfig(audioConfig)
                // 识别设置。
                .setSpeechConfig(speechConfig)
                .build();

        // 流请求。
        StreamingSpeechRequest.Builder streamingSpeechRequestBuilder = StreamingSpeechRequest.newBuilder()
                // 音频流设置。
                .setStreamingSpeechConfig(streamingSpeechConfig);

        return streamingSpeechRequestBuilder;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
