package com.databaker.hyzx.message;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

import java.util.*;




public class SocketMsg extends org.java_websocket.client.WebSocketClient {
    private static Logger logger = LoggerFactory.getLogger(SocketMsg.class);

    private Gson gson = new Gson();
    List<byte[]> listbte = new ArrayList<byte[]>();
    public SocketMsg(URI serverUri) {
        super(serverUri);
    }

    @SneakyThrows
    @Override
    public void onOpen(ServerHandshake arg0) {

        logger.info("------ 前端 onOpen ------arg0={}",arg0);
    }

    public  void sendMessage(String messsage){
        this.send(messsage);
    }
    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        logger.info("------ 前端 onClose ------arg0={},arg1={},arg2={}",arg0,arg1,arg2);
    }

    @Override
    public void onError(Exception arg0) {
        logger.info("------ 前端 onError ------arg0={}",arg0);
    }

    @SneakyThrows
    @Override
    public void onMessage(String arg0) {
         logger.info("前端接收到服务端数据：{}",arg0);

       /* JSONObject jsonObject = JSON.parseObject(arg0);
        // Map messageMap = gson.fromJson(str,Map.class);

        String data = jsonObject.get("data").toString();
        logger.info(data);
        JsonObject left_result_ = new JsonParser().parse(data).getAsJsonObject();
        String audio = left_result_.getAsJsonPrimitive("audio_data").toString();
        logger.info("              "+audio);
        listbte.add((new BASE64Decoder()).decodeBuffer(audio));

        if("1".equals(left_result_.getAsJsonPrimitive("end_flag").toString())){
            if (listbte.size()>0) {
                FileOutputStream fileOutputStream = null;
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                //拼装路径，上存储的路径 2021/6/5/xxxx.jpg
                //2021/6/5/
                String folder = dtf.format(ldt);
                String timeStamp = System.currentTimeMillis()+"";
                //xxxx
                String originalFilename = timeStamp+"_"+".mp3";
                //.jpg
                //新文件名
                String fileName = "D:/" + folder + "/" + originalFilename ;

                String localPath = "D:/" + folder + "/"  + timeStamp +"_" + ".pcm";
                createDir( "D:/" + folder+"/");
                File cloudFile = new File(localPath);
                try {
                    if (!cloudFile.exists()) {
                        try {
                            cloudFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    fileOutputStream = new FileOutputStream(cloudFile);
                    for(int i = 0;i<listbte.size();i++){
                        fileOutputStream.write(listbte.get(i), 0,listbte.get(i).length);
                    }
                    fileOutputStream.flush();

                    String newlocalPath = localPath.substring(0, localPath.length() - 3) + "mp3";
                    convertPcmToMp3(localPath, newlocalPath);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
*/

    }

}
