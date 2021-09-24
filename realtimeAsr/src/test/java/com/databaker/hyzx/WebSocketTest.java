package com.databaker.hyzx;

import com.databaker.hyzx.message.SocketMsg;
import com.google.gson.JsonObject;
import org.java_websocket.enums.ReadyState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketTest {

    private static Logger logger = LoggerFactory.getLogger(WebSocketTest.class);

    public static void main(String[] args) throws URISyntaxException {

        //URI uri = new URI("ws://10.10.50.21:9301");
        URI uri = new URI("ws://realtimeasr.kegongtech.com/websocket/1");
        SocketMsg client = new SocketMsg(uri);
        client.connect();

        while (!client.getReadyState().equals(ReadyState.OPEN)) {
          //  logger.info("连接中···请稍后");
        }
       /* JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("meeting", "点腾测试会议室测试会议2021-08-12_14:25");
        client.sendMessage(jsonObject.toString());*/

    }

}
