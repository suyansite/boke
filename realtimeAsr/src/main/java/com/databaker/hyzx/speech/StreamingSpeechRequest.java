// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

/**
 * <pre>
 * 流请求
 * </pre>
 *
 * Protobuf type {@code StreamingSpeechRequest}
 */
public  final class StreamingSpeechRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:StreamingSpeechRequest)
    StreamingSpeechRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StreamingSpeechRequest.newBuilder() to construct.
  private StreamingSpeechRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StreamingSpeechRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StreamingSpeechRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            com.databaker.hyzx.speech.StreamingSpeechConfig.Builder subBuilder = null;
            if (requestPayloadCase_ == 1) {
              subBuilder = ((com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_).toBuilder();
            }
            requestPayload_ =
                input.readMessage(com.databaker.hyzx.speech.StreamingSpeechConfig.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_);
              requestPayload_ = subBuilder.buildPartial();
            }
            requestPayloadCase_ = 1;
            break;
          }
          case 18: {
            requestPayloadCase_ = 2;
            requestPayload_ = input.readBytes();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingSpeechRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingSpeechRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.databaker.hyzx.speech.StreamingSpeechRequest.class, com.databaker.hyzx.speech.StreamingSpeechRequest.Builder.class);
  }

  private int requestPayloadCase_ = 0;
  private Object requestPayload_;
  public enum RequestPayloadCase
      implements com.google.protobuf.Internal.EnumLite {
    STREAMINGSPEECHCONFIG(1),
    AUDIODATA(2),
    REQUESTPAYLOAD_NOT_SET(0);
    private final int value;
    private RequestPayloadCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static RequestPayloadCase valueOf(int value) {
      return forNumber(value);
    }

    public static RequestPayloadCase forNumber(int value) {
      switch (value) {
        case 1: return STREAMINGSPEECHCONFIG;
        case 2: return AUDIODATA;
        case 0: return REQUESTPAYLOAD_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public RequestPayloadCase
  getRequestPayloadCase() {
    return RequestPayloadCase.forNumber(
        requestPayloadCase_);
  }

  public static final int STREAMINGSPEECHCONFIG_FIELD_NUMBER = 1;
  /**
   * <pre>
   * 音频流设置
   * </pre>
   *
   * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
   */
  public boolean hasStreamingSpeechConfig() {
    return requestPayloadCase_ == 1;
  }
  /**
   * <pre>
   * 音频流设置
   * </pre>
   *
   * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
   */
  public com.databaker.hyzx.speech.StreamingSpeechConfig getStreamingSpeechConfig() {
    if (requestPayloadCase_ == 1) {
       return (com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_;
    }
    return com.databaker.hyzx.speech.StreamingSpeechConfig.getDefaultInstance();
  }
  /**
   * <pre>
   * 音频流设置
   * </pre>
   *
   * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
   */
  public com.databaker.hyzx.speech.StreamingSpeechConfigOrBuilder getStreamingSpeechConfigOrBuilder() {
    if (requestPayloadCase_ == 1) {
       return (com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_;
    }
    return com.databaker.hyzx.speech.StreamingSpeechConfig.getDefaultInstance();
  }

  public static final int AUDIODATA_FIELD_NUMBER = 2;
  /**
   * <pre>
   * 音频数据。每个请求的音频长度最长为60秒。针对实时场景，音频输入的速度超过实时时，性能无法保障，需与依图沟通
   * </pre>
   *
   * <code>bytes audioData = 2;</code>
   */
  public com.google.protobuf.ByteString getAudioData() {
    if (requestPayloadCase_ == 2) {
      return (com.google.protobuf.ByteString) requestPayload_;
    }
    return com.google.protobuf.ByteString.EMPTY;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (requestPayloadCase_ == 1) {
      output.writeMessage(1, (com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_);
    }
    if (requestPayloadCase_ == 2) {
      output.writeBytes(
          2, (com.google.protobuf.ByteString) requestPayload_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (requestPayloadCase_ == 1) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, (com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_);
    }
    if (requestPayloadCase_ == 2) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(
            2, (com.google.protobuf.ByteString) requestPayload_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.databaker.hyzx.speech.StreamingSpeechRequest)) {
      return super.equals(obj);
    }
    com.databaker.hyzx.speech.StreamingSpeechRequest other = (com.databaker.hyzx.speech.StreamingSpeechRequest) obj;

    boolean result = true;
    result = result && getRequestPayloadCase().equals(
        other.getRequestPayloadCase());
    if (!result) return false;
    switch (requestPayloadCase_) {
      case 1:
        result = result && getStreamingSpeechConfig()
            .equals(other.getStreamingSpeechConfig());
        break;
      case 2:
        result = result && getAudioData()
            .equals(other.getAudioData());
        break;
      case 0:
      default:
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    switch (requestPayloadCase_) {
      case 1:
        hash = (37 * hash) + STREAMINGSPEECHCONFIG_FIELD_NUMBER;
        hash = (53 * hash) + getStreamingSpeechConfig().hashCode();
        break;
      case 2:
        hash = (37 * hash) + AUDIODATA_FIELD_NUMBER;
        hash = (53 * hash) + getAudioData().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.StreamingSpeechRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.databaker.hyzx.speech.StreamingSpeechRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * 流请求
   * </pre>
   *
   * Protobuf type {@code StreamingSpeechRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:StreamingSpeechRequest)
      com.databaker.hyzx.speech.StreamingSpeechRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingSpeechRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingSpeechRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.databaker.hyzx.speech.StreamingSpeechRequest.class, com.databaker.hyzx.speech.StreamingSpeechRequest.Builder.class);
    }

    // Construct using com.databaker.hyzx.speech.StreamingSpeechRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      requestPayloadCase_ = 0;
      requestPayload_ = null;
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingSpeechRequest_descriptor;
    }

    public com.databaker.hyzx.speech.StreamingSpeechRequest getDefaultInstanceForType() {
      return com.databaker.hyzx.speech.StreamingSpeechRequest.getDefaultInstance();
    }

    public com.databaker.hyzx.speech.StreamingSpeechRequest build() {
      com.databaker.hyzx.speech.StreamingSpeechRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.databaker.hyzx.speech.StreamingSpeechRequest buildPartial() {
      com.databaker.hyzx.speech.StreamingSpeechRequest result = new com.databaker.hyzx.speech.StreamingSpeechRequest(this);
      if (requestPayloadCase_ == 1) {
        if (streamingSpeechConfigBuilder_ == null) {
          result.requestPayload_ = requestPayload_;
        } else {
          result.requestPayload_ = streamingSpeechConfigBuilder_.build();
        }
      }
      if (requestPayloadCase_ == 2) {
        result.requestPayload_ = requestPayload_;
      }
      result.requestPayloadCase_ = requestPayloadCase_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.databaker.hyzx.speech.StreamingSpeechRequest) {
        return mergeFrom((com.databaker.hyzx.speech.StreamingSpeechRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.databaker.hyzx.speech.StreamingSpeechRequest other) {
      if (other == com.databaker.hyzx.speech.StreamingSpeechRequest.getDefaultInstance()) return this;
      switch (other.getRequestPayloadCase()) {
        case STREAMINGSPEECHCONFIG: {
          mergeStreamingSpeechConfig(other.getStreamingSpeechConfig());
          break;
        }
        case AUDIODATA: {
          setAudioData(other.getAudioData());
          break;
        }
        case REQUESTPAYLOAD_NOT_SET: {
          break;
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.databaker.hyzx.speech.StreamingSpeechRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.databaker.hyzx.speech.StreamingSpeechRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int requestPayloadCase_ = 0;
    private Object requestPayload_;
    public RequestPayloadCase
        getRequestPayloadCase() {
      return RequestPayloadCase.forNumber(
          requestPayloadCase_);
    }

    public Builder clearRequestPayload() {
      requestPayloadCase_ = 0;
      requestPayload_ = null;
      onChanged();
      return this;
    }


    private com.google.protobuf.SingleFieldBuilderV3<
        com.databaker.hyzx.speech.StreamingSpeechConfig, com.databaker.hyzx.speech.StreamingSpeechConfig.Builder, com.databaker.hyzx.speech.StreamingSpeechConfigOrBuilder> streamingSpeechConfigBuilder_;
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public boolean hasStreamingSpeechConfig() {
      return requestPayloadCase_ == 1;
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public com.databaker.hyzx.speech.StreamingSpeechConfig getStreamingSpeechConfig() {
      if (streamingSpeechConfigBuilder_ == null) {
        if (requestPayloadCase_ == 1) {
          return (com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_;
        }
        return com.databaker.hyzx.speech.StreamingSpeechConfig.getDefaultInstance();
      } else {
        if (requestPayloadCase_ == 1) {
          return streamingSpeechConfigBuilder_.getMessage();
        }
        return com.databaker.hyzx.speech.StreamingSpeechConfig.getDefaultInstance();
      }
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public Builder setStreamingSpeechConfig(com.databaker.hyzx.speech.StreamingSpeechConfig value) {
      if (streamingSpeechConfigBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        requestPayload_ = value;
        onChanged();
      } else {
        streamingSpeechConfigBuilder_.setMessage(value);
      }
      requestPayloadCase_ = 1;
      return this;
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public Builder setStreamingSpeechConfig(
        com.databaker.hyzx.speech.StreamingSpeechConfig.Builder builderForValue) {
      if (streamingSpeechConfigBuilder_ == null) {
        requestPayload_ = builderForValue.build();
        onChanged();
      } else {
        streamingSpeechConfigBuilder_.setMessage(builderForValue.build());
      }
      requestPayloadCase_ = 1;
      return this;
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public Builder mergeStreamingSpeechConfig(com.databaker.hyzx.speech.StreamingSpeechConfig value) {
      if (streamingSpeechConfigBuilder_ == null) {
        if (requestPayloadCase_ == 1 &&
            requestPayload_ != com.databaker.hyzx.speech.StreamingSpeechConfig.getDefaultInstance()) {
          requestPayload_ = com.databaker.hyzx.speech.StreamingSpeechConfig.newBuilder((com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_)
              .mergeFrom(value).buildPartial();
        } else {
          requestPayload_ = value;
        }
        onChanged();
      } else {
        if (requestPayloadCase_ == 1) {
          streamingSpeechConfigBuilder_.mergeFrom(value);
        }
        streamingSpeechConfigBuilder_.setMessage(value);
      }
      requestPayloadCase_ = 1;
      return this;
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public Builder clearStreamingSpeechConfig() {
      if (streamingSpeechConfigBuilder_ == null) {
        if (requestPayloadCase_ == 1) {
          requestPayloadCase_ = 0;
          requestPayload_ = null;
          onChanged();
        }
      } else {
        if (requestPayloadCase_ == 1) {
          requestPayloadCase_ = 0;
          requestPayload_ = null;
        }
        streamingSpeechConfigBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public com.databaker.hyzx.speech.StreamingSpeechConfig.Builder getStreamingSpeechConfigBuilder() {
      return getStreamingSpeechConfigFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    public com.databaker.hyzx.speech.StreamingSpeechConfigOrBuilder getStreamingSpeechConfigOrBuilder() {
      if ((requestPayloadCase_ == 1) && (streamingSpeechConfigBuilder_ != null)) {
        return streamingSpeechConfigBuilder_.getMessageOrBuilder();
      } else {
        if (requestPayloadCase_ == 1) {
          return (com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_;
        }
        return com.databaker.hyzx.speech.StreamingSpeechConfig.getDefaultInstance();
      }
    }
    /**
     * <pre>
     * 音频流设置
     * </pre>
     *
     * <code>.StreamingSpeechConfig streamingSpeechConfig = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.databaker.hyzx.speech.StreamingSpeechConfig, com.databaker.hyzx.speech.StreamingSpeechConfig.Builder, com.databaker.hyzx.speech.StreamingSpeechConfigOrBuilder>
        getStreamingSpeechConfigFieldBuilder() {
      if (streamingSpeechConfigBuilder_ == null) {
        if (!(requestPayloadCase_ == 1)) {
          requestPayload_ = com.databaker.hyzx.speech.StreamingSpeechConfig.getDefaultInstance();
        }
        streamingSpeechConfigBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.databaker.hyzx.speech.StreamingSpeechConfig, com.databaker.hyzx.speech.StreamingSpeechConfig.Builder, com.databaker.hyzx.speech.StreamingSpeechConfigOrBuilder>(
                (com.databaker.hyzx.speech.StreamingSpeechConfig) requestPayload_,
                getParentForChildren(),
                isClean());
        requestPayload_ = null;
      }
      requestPayloadCase_ = 1;
      onChanged();;
      return streamingSpeechConfigBuilder_;
    }

    /**
     * <pre>
     * 音频数据。每个请求的音频长度最长为60秒。针对实时场景，音频输入的速度超过实时时，性能无法保障，需与依图沟通
     * </pre>
     *
     * <code>bytes audioData = 2;</code>
     */
    public com.google.protobuf.ByteString getAudioData() {
      if (requestPayloadCase_ == 2) {
        return (com.google.protobuf.ByteString) requestPayload_;
      }
      return com.google.protobuf.ByteString.EMPTY;
    }
    /**
     * <pre>
     * 音频数据。每个请求的音频长度最长为60秒。针对实时场景，音频输入的速度超过实时时，性能无法保障，需与依图沟通
     * </pre>
     *
     * <code>bytes audioData = 2;</code>
     */
    public Builder setAudioData(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  requestPayloadCase_ = 2;
      requestPayload_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 音频数据。每个请求的音频长度最长为60秒。针对实时场景，音频输入的速度超过实时时，性能无法保障，需与依图沟通
     * </pre>
     *
     * <code>bytes audioData = 2;</code>
     */
    public Builder clearAudioData() {
      if (requestPayloadCase_ == 2) {
        requestPayloadCase_ = 0;
        requestPayload_ = null;
        onChanged();
      }
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:StreamingSpeechRequest)
  }

  // @@protoc_insertion_point(class_scope:StreamingSpeechRequest)
  private static final com.databaker.hyzx.speech.StreamingSpeechRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.databaker.hyzx.speech.StreamingSpeechRequest();
  }

  public static com.databaker.hyzx.speech.StreamingSpeechRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StreamingSpeechRequest>
      PARSER = new com.google.protobuf.AbstractParser<StreamingSpeechRequest>() {
    public StreamingSpeechRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StreamingSpeechRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StreamingSpeechRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<StreamingSpeechRequest> getParserForType() {
    return PARSER;
  }

  public com.databaker.hyzx.speech.StreamingSpeechRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

