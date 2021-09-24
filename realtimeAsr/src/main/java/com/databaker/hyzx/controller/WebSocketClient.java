package com.databaker.hyzx.controller;

import com.databaker.hyzx.entity.MeetingEntity;
import com.databaker.hyzx.task.VoiceThreadTask;
import com.google.gson.Gson;
import lombok.Data;
import lombok.SneakyThrows;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.databaker.hyzx.constant.Constants.*;


@Data
public class WebSocketClient  extends org.java_websocket.client.WebSocketClient  {

    private static ApplicationContext ac;

    private static Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

    private Long timestamp = 0L;

    private Gson gson = new Gson();

    private String roomId;

    private CopyOnWriteArraySet<WebSocketServer> webSocketSet;

    //private String databaker_realtime_url;

    private  URI uri;

    public WebSocketClient(URI uri,String roomId , CopyOnWriteArraySet<WebSocketServer>  webSocketSet ) throws URISyntaxException {

        super(uri);
        this.uri = uri;
        logger.info("uri={}",uri.toString());
        this.roomId = roomId;
        this.webSocketSet = webSocketSet;
        //this.databaker_realtime_url = databaker_realtime_url;

    }

    @SneakyThrows
    @Override
    public void onOpen(ServerHandshake arg0) {
        logger.info("------ WebSocket onOpen ------{}",arg0.getHttpStatusMessage());
        logger.info("------ WebSocket onOpen ------{}",arg0.getHttpStatus());
    }

    public  void sendMessage(String messsage){
        this.send(messsage);
    }
    @SneakyThrows
    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        logger.info("------ WebSocket onClose ------arg0={},arg1={},arg2={}",arg0,arg1,arg2);
        MeetingEntity meetingEntity  = MEETING_ENTITY_MAP.get(roomId);
        if(meetingEntity!=null && meetingEntity.getIsFinal()==0 && (System.currentTimeMillis() - meetingEntity.getLastTimeData()) < suspendTime){
            //URI uri = new URI(databaker_realtime_url);
            WebSocketClient client = new WebSocketClient(uri, roomId, webSocketSet);
            client.connect();
            while (!client.getReadyState().equals(ReadyState.OPEN)) {
            }
            meetingEntity.getPool().submit(new VoiceThreadTask(client));
            logger.info("客户端重新连接asr成功,roomID={}",roomId);
        }
        if(meetingEntity!=null && meetingEntity.getIsFinal()==1){
            meetingEntity.savData();
            MEETING_ENTITY_MAP.remove(roomId);
        }
    }

    @Override
    public void onError(Exception arg0) {
        logger.info("------ WebSocket onError ------arg0={}",arg0);
    }

    @SneakyThrows
    @Override
    public void onMessage(String arg0) {
        MeetingEntity meetingEntity  = MEETING_ENTITY_MAP.get(roomId);
        Map messageMap = gson.fromJson(arg0,Map.class);
            logger.info("-------- 接收到服务端数据：{}",arg0);
            if("90000.0".equals(messageMap.get("code").toString())){
                String text = messageMap.get("asr_text").toString();
                if(meetingEntity!=null && text.equals(meetingEntity.getBeforeText()) && STATEMENTONE.contains(text)){
                    return;
                }
                sendText( webSocketSet, arg0);
                String sentence_end = messageMap.get("sentence_end").toString();
                if(meetingEntity!=null){
                    if(sentence_end.equals("true")){
                        meetingEntity.buildData(messageMap,messageMap.get("trace_id").toString());
                    }
                } else {
                    closeConnect( webSocketSet);
                }
            }
    }

    /**
     * 编码
     * @param bytes
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(byte[] bytes) throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(bytes), "UTF-8");
    }

    private void sendText(CopyOnWriteArraySet<WebSocketServer> webSocketSet,String text){
        for (WebSocketServer se :webSocketSet){
            se.getSession().getAsyncRemote().sendText(text);
        }
    }

    private void closeConnect(CopyOnWriteArraySet<WebSocketServer> webSocketSet){
        for (WebSocketServer se :webSocketSet){
            try {
                se.getSession().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
