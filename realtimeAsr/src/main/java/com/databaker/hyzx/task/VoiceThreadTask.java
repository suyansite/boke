package com.databaker.hyzx.task;

import com.databaker.hyzx.controller.WebSocketClient;
import com.databaker.hyzx.entity.MeetingEntity;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import static com.databaker.hyzx.constant.Constants.*;
import static com.databaker.hyzx.utils.BASE64Utils.encode;

public class VoiceThreadTask implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(VoiceThreadTask.class);

    private WebSocketClient webSocketClient;

    public VoiceThreadTask(){
    }

    public VoiceThreadTask(WebSocketClient webSocketClient){
        this.webSocketClient = webSocketClient;
    }
    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1);
        logger.info("实时转写音频RoomID={}",webSocketClient.getRoomId());
        MeetingEntity audoEntity = MEETING_ENTITY_MAP.get(webSocketClient.getRoomId());
        LinkedBlockingQueue<byte[]> queue = audoEntity.getQueue();

        if(audoEntity!=null && audoEntity.getIsFinal()==0){
            int index = 0;
            Map<String, Object> pram = new HashMap<String, Object>();
            pram.put("access_token",TOKEN_MAP.get("token"));
            pram.put("version", "1.0");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("audio_format", "pcm");
            data.put("sample_rate", 16000);
            data.put("speech_type", 1);
            data.put("add_pct", true);
            data.put("domain", "common");

            for (;;){
                if(audoEntity.getIsFinal()!=0){
                    data.put("req_idx", -index);
                    data.put("audio_data", queue.poll().toString());
                } else {
                    data.put("req_idx", index);
                    String dataStr = encode(queue.take());
                    data.put("audio_data", dataStr);
                }
                index++;
                pram.put("asr_params", data);
                net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(pram);
                logger.info("实时发送数据{}");
                webSocketClient.sendMessage(jsonObject.toString());
                if(audoEntity.getIsFinal()!=0){
                    break;
                }
            }
                logger.info("会议暂时没有音频");
            }

    }

}
