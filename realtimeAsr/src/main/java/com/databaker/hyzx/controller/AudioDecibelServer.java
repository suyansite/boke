package com.databaker.hyzx.controller;

import com.google.gson.Gson;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.databaker.hyzx.utils.AudioUtil.*;
import static com.databaker.hyzx.utils.BASE64Utils.decode;


/**
  * @Description: websocket的具体实现类
  * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的,
  * 但在springboot中连容器都是spring管理的。
  *  虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean,
  *  所以可以用一个静态set保存起来。
  */
//计算每个音频包的平均分贝值
@ServerEndpoint("/recordDecibel/")
@Component
@Data
public class AudioDecibelServer {

    private static Logger logger = LoggerFactory.getLogger(AudioDecibelServer.class);

    private  Gson gson = new Gson();

    private static  AtomicInteger onlineCount = new AtomicInteger(0);

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
    * 连接建立成功调用的方法
    */
    @OnOpen
    public void onOpen(Session session) throws URISyntaxException, UnsupportedEncodingException {

        this.session = session;
        onlineCount.incrementAndGet(); //在线数加1
        logger.info("链接数={}",onlineCount);

    }
       /**
      * 连接关闭调用的方法
      */
        @OnClose
        public void onClose() {
            onlineCount.decrementAndGet(); // 在线数减1

        }

        /**
       * 收到客户端消息后调用的方法
       * @param message 客户端发送过来的消息
         */
         @OnMessage
         public void onMessage(String  message) throws IOException {
             //logger.info("message={}",message);
             Map messageMap = gson.fromJson(message,Map.class);
             String data = messageMap.get("audio_data").toString();
             try {
                 byte[] b = decode(data);
                 short[] buffer = byteArray2ShortArray(b, b.length/2);
                 double volume = calcDecibelLevel( buffer,  buffer.length);
                 int prms = (int)volume;
                 logger.info("分贝={}",prms);
                 session.getAsyncRemote().sendText(prms+"");
             } catch (Exception e) {
                 e.printStackTrace();
                 session.close();
             }


         }
         /**
         * 发生错误时调用
         */
        @OnError
        public void onError(Session session, Throwable error) throws IOException {
            onlineCount.decrementAndGet(); // 在线数减1
            session.close();
        }

}
