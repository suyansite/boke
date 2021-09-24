package com.databaker.hyzx.controller;

import com.databaker.hyzx.config.PropertiesConfig;
import com.databaker.hyzx.entity.MeetingEntity;
import com.databaker.hyzx.task.VoiceThreadTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.java_websocket.enums.ReadyState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.databaker.hyzx.constant.Constants.*;
import static com.databaker.hyzx.utils.BASE64Utils.decode;
import static com.databaker.hyzx.utils.BASE64Utils.decodeToString;


/**
  * @Description: websocket的具体实现类
  * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的,
  * 但在springboot中连容器都是spring管理的。
  *  虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean,
  *  所以可以用一个静态set保存起来。
   *  roomid:会议id
   *  hotWordId:热词配置id
   *    openid:微信号openid
   *    asrType:转写引擎类型1：依图；2：标贝
  */

@ServerEndpoint("/websocket/{roomId}/{hotWordId}/{openid}/{asrType}")
@Component
@Data
public class WebSocketServer implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static String databaker_realtime_url;

    private static String hotWordUrl;

    private static String rootPath;

    private static ApplicationContext ac;

    //用来存放每个客户端对应的WebSocket对象。
    private static Map<String,CopyOnWriteArraySet<WebSocketServer>> webSocketMap = new HashMap<String,CopyOnWriteArraySet<WebSocketServer>>();

    private  Gson gson = new Gson();

    private static  AtomicInteger onlineCount = new AtomicInteger(0);

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("hyzx-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(2, 20,
            300L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(200), namedThreadFactory);

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String roomId;

    private RealTimeAsr realTimeAsr;

    private String asrType = "1";//asr转写引擎，1：依图；2：标贝

    /**
    * 连接建立成功调用的方法
    */
    @OnOpen
    public void onOpen(@PathParam("roomId") String roomId,@PathParam("openid") String openid,@PathParam("hotWordId") String hotWordId,@PathParam("asrType") String asrType,Session session) throws URISyntaxException, UnsupportedEncodingException {
        if(StringUtils.isEmpty(roomId)) {
            logger.info("会议id不能为空");
            this.onClose();
            return;
        }
        logger.info("热词hotWordId{},roomid={},databaker_realtime_url={}",hotWordId,roomId,databaker_realtime_url);
        this.roomId = roomId;
        this.session = session;
        CopyOnWriteArraySet<WebSocketServer>  set = webSocketMap.get(roomId);
        if(set!=null){
            set.add(this);
        }else {
            set =  new CopyOnWriteArraySet<WebSocketServer>();
            set.add(this);
        }
       webSocketMap.put(roomId,set);
       onlineCount.incrementAndGet(); //在线数加1
       MeetingEntity meetingentity = MEETING_ENTITY_MAP.get(roomId);
        if(meetingentity!=null){
            /*JsonArray jsonArray = meetingentity.getHistoryTextArray();
            if(jsonArray.size()>0){
                for (int i = 0;i<jsonArray.size();i++){
                    this.session.getAsyncRemote().sendText(jsonArray.get(i).toString());
                }
            }*/
            if("1".equals(asrType)){
                if( meetingentity.getRealTimeAsr()==null) {
                    meetingentity.setRealTimeAsr(new RealTimeAsr(roomId, hotWordId,hotWordUrl));
                    logger.info("重新创建会议依图链接,roomId={}" ,roomId);
                    realTimeAsr = meetingentity.getRealTimeAsr();
                }
            } else {
                //meetingentity.setAsrType("2");
                this.asrType = "2";
                pool.submit(new SubThread(roomId,set));
            }

        } else {
            logger.info("新建会议,roomId={},hotWordUrl={}" ,roomId,hotWordUrl);
            if("1".equals(asrType)){
                meetingentity = new MeetingEntity(roomId,hotWordId,hotWordUrl);
                meetingentity.getRealTimeAsr().setSet(set);
                realTimeAsr = meetingentity.getRealTimeAsr();
            } else {
                meetingentity = new MeetingEntity(roomId);
                //meetingentity.setAsrType("2");
                this.asrType = "2";
                pool.submit(new SubThread(roomId,set));
            }
            if(!StringUtils.isEmpty(openid)){
                meetingentity.setOpenid(openid);
            }
            meetingentity.setRootPath(rootPath);
            MEETING_ENTITY_MAP.put(roomId,meetingentity);
        }
        logger.info("建会完毕,roomId={}" ,roomId);
    }
       /**
      * 连接关闭调用的方法
      */
        @OnClose
        public void onClose() {
            onlineCount.decrementAndGet(); // 在线数减1
            CopyOnWriteArraySet<WebSocketServer>  set = webSocketMap.get(roomId);
            set.remove(this);
            webSocketMap.put(roomId,set);
            logger.info("关闭方法,在线链接数={},roomid={}",onlineCount,roomId);
        }

        /**
       * 收到客户端消息后调用的方法
       * @param message 客户端发送过来的消息
         */
         @OnMessage
         public void onMessage(String  message) {
             try {

                 Map messageMap = gson.fromJson(message,Map.class);
                 String isFinal = messageMap.get("isFinal").toString();
                 Object openid = messageMap.get("openid");

                 //logger.info("isFinal={}",isFinal);
                 if("0".equals(isFinal)){//会议进行中
                     String data = messageMap.get("audio_data").toString();
                     if(!StringUtils.isEmpty(data)){
                         byte[] b = decode(data);
                         logger.info("音频长度={},roomid={}",b.length,roomId);
                         MeetingEntity meetingentity = MEETING_ENTITY_MAP.get(roomId);
                         meetingentity.getQueue().offer(b);
                         meetingentity.getFileQueue().offer(b);
                         //meetingentity.mergeAudio();
                         meetingentity.setLastTimeData(System.currentTimeMillis());
                         if(openid!=null && !StringUtils.isEmpty(openid.toString())){
                             meetingentity.setOpenid(openid.toString());
                         }
                         Object url_callback = messageMap.get("url_callback");
                         if(url_callback!=null){
                             meetingentity.setUrl_callback(url_callback.toString());
                         }
                        // logger.info("数据发送中,roomId={}" ,roomId);

                     }
                     if("1".equals(asrType)){
                         if(realTimeAsr!=null){
                             realTimeAsr.realTimeTransform();
                         }

                     }

                 } else if("1".equals(isFinal)){//会议结束
                     MeetingEntity meetingentity = MEETING_ENTITY_MAP.get(roomId);
                     meetingentity.setIsFinal(1);
                     /*if("0".equals(ASR_TYPE_MAP.get("type"))){
                         realTimeAsr.getClient().close();
                     }*/
                     //this.session.close();//客户端关闭链接
                 } else if("2".equals(isFinal)){//删除会议
                     MeetingEntity meetingentity = MEETING_ENTITY_MAP.get(roomId);
                     meetingentity.setIsFinal(2);
                     if("1".equals(asrType)){
                         if(realTimeAsr!=null){
                             realTimeAsr.getClient().close();
                         }
                     }
                     MEETING_ENTITY_MAP.remove(roomId);
                     this.session.close();
                 }
             }catch (Exception e){
                 e.printStackTrace();
             }

         }
         /**
         * 发生错误时调用
         */
        @OnError
        public void onError(Session session, Throwable error) {

            try {
                this.getSession().close();
                if("1".equals(this.asrType)){
                    if(realTimeAsr!=null){
                        realTimeAsr.getClient().shutdownChannel();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            onlineCount.decrementAndGet(); // 在线数减1
            webSocketMap.remove(roomId);
            logger.info("在线链接数={},roomid={}",onlineCount,roomId);
        }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
        PropertiesConfig properties = (PropertiesConfig)ac.getBean("propertiesConfig");
        databaker_realtime_url = properties.getDatabaker_realtime_url();
        rootPath = properties.getRootPath();
        logger.info("初始化组件hotWordUrl={}",properties.getHotWordUrl());
        hotWordUrl = properties.getHotWordUrl();
    }

    class  SubThread implements Runnable{

        private String roomId = "";
        private CopyOnWriteArraySet<WebSocketServer>  set = null;
        public SubThread(){
        }
        public SubThread(String roomId,CopyOnWriteArraySet<WebSocketServer>  set){
            this.roomId = roomId;
            this.set = set;
        }
        @SneakyThrows
        @Override
        public void run() {
            long begin = System.currentTimeMillis();
                URI uri = new URI(databaker_realtime_url);
                WebSocketClient client = new WebSocketClient(uri, roomId, set);
                client.connect();
                while (!client.getReadyState().equals(ReadyState.OPEN)) {

                }
                MeetingEntity meetingEntity  = MEETING_ENTITY_MAP.get(roomId);
                logger.info("客户端连接asr成功,roomID={},耗时{}毫秒",roomId,System.currentTimeMillis()-begin);
                meetingEntity.getPool().submit(new VoiceThreadTask(client));
                logger.info("连接成功提交任务！,roomId={},耗时{}毫秒" ,roomId,System.currentTimeMillis()-begin);

        }
    }


}
