package com.databaker.hyzx.entity;

import com.databaker.hyzx.controller.RealTimeAsr;
import com.databaker.hyzx.utils.HttpUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.databaker.hyzx.utils.AudioUtil.*;
@Data
public class MeetingEntity {

    private static Logger logger = LoggerFactory.getLogger(MeetingEntity.class);

    private String roomID;//会议id

    private RealTimeAsr realTimeAsr = null;//依图实时转写类

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("res-pool-%d").build();

    private ExecutorService pool = new ThreadPoolExecutor(0, 1,
            300L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(1), namedThreadFactory,new ThreadPoolExecutor.DiscardOldestPolicy() );

    private LinkedBlockingQueue<byte[]> fileQueue = new LinkedBlockingQueue<byte[]>();//音频数据保存在该队列,最终生成音频文件

    private LinkedBlockingQueue<byte[]> queue = new LinkedBlockingQueue<byte[]>();//实时转写队列

    private LinkedBlockingQueue<byte[]> queue_tmp = new LinkedBlockingQueue<byte[]>();//临时存放音频队列

    private StringBuilder text =  new StringBuilder();//所有转写得到的文本

    private Double eos = 0D;//文本开始时间

    private Double sos = 0D;//文本结束时间

    private StringBuilder paragraph = new StringBuilder();//段落文本

    private String beforeText = "";//前一个文本

    private JsonArray textArray = new JsonArray();//转写结果，json格式

    private JsonArray historyTextArray = new JsonArray();//前端页面刷新时历史文本的json格式，已分段

    private int isFinal = 0;//0:会中;1:结束;2:删除

    private Long lastTimeData = System.currentTimeMillis();//接收最后一个音频包的时间戳

    private String rootPath ;

    //private Long lastTimeConnect = 0l;//同一个会议最后一次链接的时间戳

    private String openid = null;//用户账号openID

    private String url_callback;//转写完成后的回调地址

    //private String asrType = "1";//asr转写引擎，1：依图；2：标贝

    public MeetingEntity(String roomId,String hotWordId,String hotWordUrl) {
        this.roomID = roomId;
        this.realTimeAsr = new RealTimeAsr(roomID, hotWordId,hotWordUrl);

    }

    public MeetingEntity(String roomId) {
        this.roomID = roomId;
    }

    public  void buildData(Map map,String streamId){
        JsonObject jsonObject = new JsonObject();
        String asr_text = map.get("asr_text").toString();
        text.append(asr_text);
        beforeText = asr_text;
        jsonObject.addProperty("result",asr_text);
        Double sos = Double.parseDouble(map.get("sos").toString());
        jsonObject.addProperty("sos",sos);
        Double eos = Double.parseDouble(map.get("eos").toString());
        jsonObject.addProperty("eos",eos);
        jsonObject.addProperty("success","yes");
        textArray.add(jsonObject);
        paragraph.append(text);
        if(paragraph.toString().length()>100){
            JsonObject json_qianduan = new JsonObject();//这个对象给前端分段用
            json_qianduan.addProperty("code",90000);
            json_qianduan.addProperty("result",paragraph.toString());
            json_qianduan.addProperty("success","yes");
            json_qianduan.addProperty("trace_id", streamId);
            json_qianduan.addProperty( "sentence_end","true");
            historyTextArray.add(json_qianduan);
            paragraph = new StringBuilder();
        }
    }


    public synchronized void savData(){
           if(fileQueue.size()==0 ){
               logger.info("{}会议无文件可以保存！",roomID);
               return;
           }
            Map<String,File> map = creatFile(fileQueue, rootPath);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("meeting_id", roomID);
            jsonObject.addProperty("code", 20000);
            jsonObject.addProperty("info", "Success");
            JsonObject textObject = new JsonObject();
            textObject.add("left_result_array",textArray);
            textObject.addProperty("left_result", text.toString());
            jsonObject.add("text", textObject);
            File audioFile =  map.get("audioFile");
            jsonObject.addProperty("filename", audioFile.getName());
            jsonObject.addProperty("file_path", audioFile.getPath());
            jsonObject.addProperty("file_url", "mp3/"+audioFile.getPath().substring(rootPath.length(),audioFile.getPath().length()));
            int vf_time = getDuration(audioFile.getPath());
            jsonObject.addProperty("file_time", vf_time);//录音时长，单位：秒
            jsonObject.addProperty("openid",openid);
            File jsonFile =  map.get("jsonFile");
            logger.info("jsonFile.getPath()={}",jsonFile.getPath());
            String jsonPath = "json"+jsonFile.getPath().substring(rootPath.length(),jsonFile.getPath().lastIndexOf("."))+".json";

            logger.info("jsonPath={}",jsonPath);
            jsonObject.addProperty("json_url", jsonPath);
            FileInputStream fio = null;
            try {
                fio = new FileInputStream(audioFile.getPath());
                FileChannel fc = fio.getChannel();
                String vf_size = formatFileSize(fc.size());
                jsonObject.addProperty("file_size", vf_size);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fio.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            logger.info("jsonObject.toString()={}", jsonObject.toString());
            logger.info("url_callback={}", url_callback);
            String postResult = HttpUtils.post(url_callback, jsonObject.toString(), null);
            logger.info("result = {}", postResult);

    }

    public void mergeAudio(){
        byte[] voiceByte = {};
        if(realTimeAsr==null){
            if(queue_tmp.size()>=5){
                List<byte[]> list = new ArrayList<byte[]>(5);
                for (int i = 0;i<5;i++){
                    list.add(queue_tmp.poll());
                }
                voiceByte = mergeByte( list);
                queue.offer(voiceByte);
                fileQueue.offer(voiceByte);
            }
        } else {
            if(queue_tmp.size()>=6){
                List<byte[]> list = new ArrayList<byte[]>(6);
                for (int i = 0;i<6;i++){
                    list.add(queue_tmp.poll());
                }
                voiceByte = mergeByte( list);
                queue.offer(voiceByte);
                fileQueue.offer(voiceByte);
            }
        }
    }

    public  byte[]  mergeByte(List<byte[]> list){
        byte[] voiceByte = {};
        for (int i = 0;i<list.size();i++){
            voiceByte =  (byte[]) ArrayUtils.addAll(voiceByte, list.get(i));
            //logger.info("音频数组voiceByte大小={}",voiceByte.length);
        }
        return voiceByte;
    }
}
