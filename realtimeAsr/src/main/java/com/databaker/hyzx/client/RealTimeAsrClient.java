package com.databaker.hyzx.client;

import com.databaker.hyzx.service.ClientAuthInterceptorService;
import com.databaker.hyzx.speech.SpeechRecognitionGrpc;
import com.databaker.hyzx.speech.StreamingSpeechRequest;
import com.databaker.hyzx.speech.StreamingSpeechResponse;
import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

import static com.databaker.hyzx.constant.Constants.MEETING_ENTITY_MAP;

/**
 */
@Component
@Scope(value = "prototype")
@Data
public class RealTimeAsrClient {

    Logger logger = LoggerFactory.getLogger(RealTimeAsrClient.class);

    private final LinkedBlockingQueue<StreamingSpeechResponse> responses;
    ManagedChannel channel;
    @Value("${asr.host}")
    //private String asrHost = "stream-asr-prod.yitutech.com";
    private String asrHost ;
    @Value("${asr.port}")
    //private Integer asrPort = 50051;
    private Integer asrPort;
    @Value("${dev.id}")
    //private Integer devId = 22009;
    private Integer devId ;
    @Value("${dev.key}")
    //private String devKey = "OWNhOTNjYzk4NTg2NGM2ZGJhZjExZTY3MTM1NDZhNzI=";
    private String devKey;
    private StreamObserver<StreamingSpeechRequest> requestObserver = null;
    private StreamingSpeechRequest.Builder requestBuilder;
    private String globalStreamId;


    private String  roomId;

    private boolean isFinished;

    public RealTimeAsrClient() {
        // 初始化结果队列
        responses = new LinkedBlockingQueue<>();
        // 是否转写结束标志位
        isFinished = false;
    }

    /**
     * 建立链接
     */
    public void buildConnection(StreamingSpeechRequest.Builder requestBuilder) {
        channel = ManagedChannelBuilder.forAddress(
                asrHost, asrPort).intercept(new ClientAuthInterceptorService(devId, devKey)).usePlaintext(true).build();
        SpeechRecognitionGrpc.SpeechRecognitionStub stub = SpeechRecognitionGrpc.newStub(channel);
        requestObserver = stub.recognizeStream(new StreamObserver<StreamingSpeechResponse>() {
           @Override
            public void onNext(StreamingSpeechResponse response) {
               //logger.info("asr服务端onNext");
                isFinished = false;
                if (globalStreamId == null || globalStreamId.length() == 0) {
                    globalStreamId = response.getGlobalStreamId();
                }
                responses.offer(response);
            }

            @Override
            public void onError(Throwable t) {
                logger.info("asr服务端{},roomId{}",t.getMessage(),roomId);
                /*MeetingEntity meetingentity = MEETING_ENTITY_MAP.get(roomId);
                if(meetingentity!=null && meetingentity.getIsFinal() == 0){
                       RealTimeAsr asr = new RealTimeAsr(new RealTimeAsrClient(roomId));
                       meetingentity.setRealTimeAsr(asr);
                       MEETING_ENTITY_MAP.put(roomId, meetingentity);
                       isFinished = false;
                       postAudio(new byte[0]);
                } else {
                    isFinished = true;
                }*/
                isFinished = true;
               /* if("UNKNOWN".equals(t.getMessage())){//该异常情况不结束转写

                } else {
                    isFinished = true;
                }*/
                // System.out.println(t.getMessage());

            }

            @Override
            public void onCompleted() {
                logger.info("asr服务端onCompleted");
                isFinished = true;
            }
        });
        this.requestBuilder = requestBuilder;
        // 发送音频配置
        requestObserver.onNext(requestBuilder.build());
    }

    /**
     * 获取全局ID
     */
    public String getGlobalStreamId() {
        return globalStreamId;
    }

    /**
     * 发送待转写语音
     */
    public void postAudio(byte[] buf) {
        StreamingSpeechRequest request = requestBuilder.setAudioData(ByteString.copyFrom(buf)).build();
        requestObserver.onNext(request);
    }

    /**
     * 获取转写结果
     */
    public LinkedBlockingQueue<StreamingSpeechResponse> getResult() {
        return responses;
    }

    /**
     * 清空结果队列
     */
    public void clearResult() {
        responses.clear();
    }

    /**
     * 关闭发送链接
     */
    public void close() {
        requestObserver.onCompleted();
    }

    /**
     * 是否转写结束
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * 断开grpc连接
     */
    public void shutdownChannel() {
        channel.shutdown();
    }
}
