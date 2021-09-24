package com.databaker.hyzx.speech;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 * 音频流识别服务。
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: real_time_asr.proto")
public final class SpeechRecognitionGrpc {

  private SpeechRecognitionGrpc() {}

  public static final String SERVICE_NAME = "SpeechRecognition";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.databaker.hyzx.speech.StreamingSpeechRequest,
      com.databaker.hyzx.speech.StreamingSpeechResponse> METHOD_RECOGNIZE_STREAM =
      io.grpc.MethodDescriptor.<com.databaker.hyzx.speech.StreamingSpeechRequest, com.databaker.hyzx.speech.StreamingSpeechResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "SpeechRecognition", "RecognizeStream"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.databaker.hyzx.speech.StreamingSpeechRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.databaker.hyzx.speech.StreamingSpeechResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SpeechRecognitionStub newStub(io.grpc.Channel channel) {
    return new SpeechRecognitionStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SpeechRecognitionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SpeechRecognitionBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SpeechRecognitionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SpeechRecognitionFutureStub(channel);
  }

  /**
   * <pre>
   * 音频流识别服务。
   * </pre>
   */
  public static abstract class SpeechRecognitionImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 传入metadata "x-api-key"作为验证。
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.databaker.hyzx.speech.StreamingSpeechRequest> recognizeStream(
        io.grpc.stub.StreamObserver<com.databaker.hyzx.speech.StreamingSpeechResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_RECOGNIZE_STREAM, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_RECOGNIZE_STREAM,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.databaker.hyzx.speech.StreamingSpeechRequest,
                com.databaker.hyzx.speech.StreamingSpeechResponse>(
                  this, METHODID_RECOGNIZE_STREAM)))
          .build();
    }
  }

  /**
   * <pre>
   * 音频流识别服务。
   * </pre>
   */
  public static final class SpeechRecognitionStub extends io.grpc.stub.AbstractStub<SpeechRecognitionStub> {
    private SpeechRecognitionStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SpeechRecognitionStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SpeechRecognitionStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SpeechRecognitionStub(channel, callOptions);
    }

    /**
     * <pre>
     * 传入metadata "x-api-key"作为验证。
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.databaker.hyzx.speech.StreamingSpeechRequest> recognizeStream(
        io.grpc.stub.StreamObserver<com.databaker.hyzx.speech.StreamingSpeechResponse> responseObserver) {
      return ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(METHOD_RECOGNIZE_STREAM, getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * 音频流识别服务。
   * </pre>
   */
  public static final class SpeechRecognitionBlockingStub extends io.grpc.stub.AbstractStub<SpeechRecognitionBlockingStub> {
    private SpeechRecognitionBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SpeechRecognitionBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SpeechRecognitionBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SpeechRecognitionBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   * 音频流识别服务。
   * </pre>
   */
  public static final class SpeechRecognitionFutureStub extends io.grpc.stub.AbstractStub<SpeechRecognitionFutureStub> {
    private SpeechRecognitionFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SpeechRecognitionFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SpeechRecognitionFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SpeechRecognitionFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_RECOGNIZE_STREAM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SpeechRecognitionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SpeechRecognitionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECOGNIZE_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.recognizeStream(
              (io.grpc.stub.StreamObserver<com.databaker.hyzx.speech.StreamingSpeechResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class SpeechRecognitionDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.databaker.hyzx.speech.StreamingProtos.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SpeechRecognitionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SpeechRecognitionDescriptorSupplier())
              .addMethod(METHOD_RECOGNIZE_STREAM)
              .build();
        }
      }
    }
    return result;
  }
}
