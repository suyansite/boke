// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

/**
 * <pre>
 * 转写分解
 * </pre>
 *
 * Protobuf type {@code StreamingTranscriptionPiece}
 */
public  final class StreamingTranscriptionPiece extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:StreamingTranscriptionPiece)
    StreamingTranscriptionPieceOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StreamingTranscriptionPiece.newBuilder() to construct.
  private StreamingTranscriptionPiece(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StreamingTranscriptionPiece() {
    transcribedText_ = "";
    beginTimestamp_ = 0L;
    endTimestamp_ = 0L;
    transcribedType_ = 0;
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StreamingTranscriptionPiece(
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
            String s = input.readStringRequireUtf8();

            transcribedText_ = s;
            break;
          }
          case 16: {

            beginTimestamp_ = input.readInt64();
            break;
          }
          case 24: {

            endTimestamp_ = input.readInt64();
            break;
          }
          case 32: {

            transcribedType_ = input.readInt32();
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
    return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingTranscriptionPiece_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingTranscriptionPiece_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.databaker.hyzx.speech.StreamingTranscriptionPiece.class, com.databaker.hyzx.speech.StreamingTranscriptionPiece.Builder.class);
  }

  public static final int TRANSCRIBEDTEXT_FIELD_NUMBER = 1;
  private volatile Object transcribedText_;
  /**
   * <pre>
   * 转写分解结果。
   * </pre>
   *
   * <code>string transcribedText = 1;</code>
   */
  public String getTranscribedText() {
    Object ref = transcribedText_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      transcribedText_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 转写分解结果。
   * </pre>
   *
   * <code>string transcribedText = 1;</code>
   */
  public com.google.protobuf.ByteString
      getTranscribedTextBytes() {
    Object ref = transcribedText_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      transcribedText_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BEGINTIMESTAMP_FIELD_NUMBER = 2;
  private long beginTimestamp_;
  /**
   * <pre>
   * 分解开始时间（音频开始时间为0）。
   * </pre>
   *
   * <code>int64 beginTimestamp = 2;</code>
   */
  public long getBeginTimestamp() {
    return beginTimestamp_;
  }

  public static final int ENDTIMESTAMP_FIELD_NUMBER = 3;
  private long endTimestamp_;
  /**
   * <pre>
   * 分解结束时间（音频开始时间为0）。
   * </pre>
   *
   * <code>int64 endTimestamp = 3;</code>
   */
  public long getEndTimestamp() {
    return endTimestamp_;
  }

  public static final int TRANSCRIBEDTYPE_FIELD_NUMBER = 4;
  private int transcribedType_;
  /**
   * <pre>
   * 转写结果的类型，一个结果可以对应多的状态, 为以下状态按位与。
   *    TOKEN = 1;
   *    PUNCTUATION = 2;
   *    NUMBER = 4;
   *    PATCH = 8;
   *    DISFLUENCY = 16;
   * </pre>
   *
   * <code>int32 transcribedType = 4;</code>
   */
  public int getTranscribedType() {
    return transcribedType_;
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
    if (!getTranscribedTextBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, transcribedText_);
    }
    if (beginTimestamp_ != 0L) {
      output.writeInt64(2, beginTimestamp_);
    }
    if (endTimestamp_ != 0L) {
      output.writeInt64(3, endTimestamp_);
    }
    if (transcribedType_ != 0) {
      output.writeInt32(4, transcribedType_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTranscribedTextBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, transcribedText_);
    }
    if (beginTimestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, beginTimestamp_);
    }
    if (endTimestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, endTimestamp_);
    }
    if (transcribedType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, transcribedType_);
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
    if (!(obj instanceof com.databaker.hyzx.speech.StreamingTranscriptionPiece)) {
      return super.equals(obj);
    }
    com.databaker.hyzx.speech.StreamingTranscriptionPiece other = (com.databaker.hyzx.speech.StreamingTranscriptionPiece) obj;

    boolean result = true;
    result = result && getTranscribedText()
        .equals(other.getTranscribedText());
    result = result && (getBeginTimestamp()
        == other.getBeginTimestamp());
    result = result && (getEndTimestamp()
        == other.getEndTimestamp());
    result = result && (getTranscribedType()
        == other.getTranscribedType());
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
    hash = (37 * hash) + TRANSCRIBEDTEXT_FIELD_NUMBER;
    hash = (53 * hash) + getTranscribedText().hashCode();
    hash = (37 * hash) + BEGINTIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getBeginTimestamp());
    hash = (37 * hash) + ENDTIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getEndTimestamp());
    hash = (37 * hash) + TRANSCRIBEDTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getTranscribedType();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece parseFrom(
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
  public static Builder newBuilder(com.databaker.hyzx.speech.StreamingTranscriptionPiece prototype) {
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
   * 转写分解
   * </pre>
   *
   * Protobuf type {@code StreamingTranscriptionPiece}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:StreamingTranscriptionPiece)
      com.databaker.hyzx.speech.StreamingTranscriptionPieceOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingTranscriptionPiece_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingTranscriptionPiece_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.databaker.hyzx.speech.StreamingTranscriptionPiece.class, com.databaker.hyzx.speech.StreamingTranscriptionPiece.Builder.class);
    }

    // Construct using com.databaker.hyzx.speech.StreamingTranscriptionPiece.newBuilder()
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
      transcribedText_ = "";

      beginTimestamp_ = 0L;

      endTimestamp_ = 0L;

      transcribedType_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_StreamingTranscriptionPiece_descriptor;
    }

    public com.databaker.hyzx.speech.StreamingTranscriptionPiece getDefaultInstanceForType() {
      return com.databaker.hyzx.speech.StreamingTranscriptionPiece.getDefaultInstance();
    }

    public com.databaker.hyzx.speech.StreamingTranscriptionPiece build() {
      com.databaker.hyzx.speech.StreamingTranscriptionPiece result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.databaker.hyzx.speech.StreamingTranscriptionPiece buildPartial() {
      com.databaker.hyzx.speech.StreamingTranscriptionPiece result = new com.databaker.hyzx.speech.StreamingTranscriptionPiece(this);
      result.transcribedText_ = transcribedText_;
      result.beginTimestamp_ = beginTimestamp_;
      result.endTimestamp_ = endTimestamp_;
      result.transcribedType_ = transcribedType_;
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
      if (other instanceof com.databaker.hyzx.speech.StreamingTranscriptionPiece) {
        return mergeFrom((com.databaker.hyzx.speech.StreamingTranscriptionPiece)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.databaker.hyzx.speech.StreamingTranscriptionPiece other) {
      if (other == com.databaker.hyzx.speech.StreamingTranscriptionPiece.getDefaultInstance()) return this;
      if (!other.getTranscribedText().isEmpty()) {
        transcribedText_ = other.transcribedText_;
        onChanged();
      }
      if (other.getBeginTimestamp() != 0L) {
        setBeginTimestamp(other.getBeginTimestamp());
      }
      if (other.getEndTimestamp() != 0L) {
        setEndTimestamp(other.getEndTimestamp());
      }
      if (other.getTranscribedType() != 0) {
        setTranscribedType(other.getTranscribedType());
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
      com.databaker.hyzx.speech.StreamingTranscriptionPiece parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.databaker.hyzx.speech.StreamingTranscriptionPiece) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object transcribedText_ = "";
    /**
     * <pre>
     * 转写分解结果。
     * </pre>
     *
     * <code>string transcribedText = 1;</code>
     */
    public String getTranscribedText() {
      Object ref = transcribedText_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        transcribedText_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <pre>
     * 转写分解结果。
     * </pre>
     *
     * <code>string transcribedText = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTranscribedTextBytes() {
      Object ref = transcribedText_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        transcribedText_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 转写分解结果。
     * </pre>
     *
     * <code>string transcribedText = 1;</code>
     */
    public Builder setTranscribedText(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      transcribedText_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 转写分解结果。
     * </pre>
     *
     * <code>string transcribedText = 1;</code>
     */
    public Builder clearTranscribedText() {

      transcribedText_ = getDefaultInstance().getTranscribedText();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 转写分解结果。
     * </pre>
     *
     * <code>string transcribedText = 1;</code>
     */
    public Builder setTranscribedTextBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      transcribedText_ = value;
      onChanged();
      return this;
    }

    private long beginTimestamp_ ;
    /**
     * <pre>
     * 分解开始时间（音频开始时间为0）。
     * </pre>
     *
     * <code>int64 beginTimestamp = 2;</code>
     */
    public long getBeginTimestamp() {
      return beginTimestamp_;
    }
    /**
     * <pre>
     * 分解开始时间（音频开始时间为0）。
     * </pre>
     *
     * <code>int64 beginTimestamp = 2;</code>
     */
    public Builder setBeginTimestamp(long value) {

      beginTimestamp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 分解开始时间（音频开始时间为0）。
     * </pre>
     *
     * <code>int64 beginTimestamp = 2;</code>
     */
    public Builder clearBeginTimestamp() {

      beginTimestamp_ = 0L;
      onChanged();
      return this;
    }

    private long endTimestamp_ ;
    /**
     * <pre>
     * 分解结束时间（音频开始时间为0）。
     * </pre>
     *
     * <code>int64 endTimestamp = 3;</code>
     */
    public long getEndTimestamp() {
      return endTimestamp_;
    }
    /**
     * <pre>
     * 分解结束时间（音频开始时间为0）。
     * </pre>
     *
     * <code>int64 endTimestamp = 3;</code>
     */
    public Builder setEndTimestamp(long value) {

      endTimestamp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 分解结束时间（音频开始时间为0）。
     * </pre>
     *
     * <code>int64 endTimestamp = 3;</code>
     */
    public Builder clearEndTimestamp() {

      endTimestamp_ = 0L;
      onChanged();
      return this;
    }

    private int transcribedType_ ;
    /**
     * <pre>
     * 转写结果的类型，一个结果可以对应多的状态, 为以下状态按位与。
     *    TOKEN = 1;
     *    PUNCTUATION = 2;
     *    NUMBER = 4;
     *    PATCH = 8;
     *    DISFLUENCY = 16;
     * </pre>
     *
     * <code>int32 transcribedType = 4;</code>
     */
    public int getTranscribedType() {
      return transcribedType_;
    }
    /**
     * <pre>
     * 转写结果的类型，一个结果可以对应多的状态, 为以下状态按位与。
     *    TOKEN = 1;
     *    PUNCTUATION = 2;
     *    NUMBER = 4;
     *    PATCH = 8;
     *    DISFLUENCY = 16;
     * </pre>
     *
     * <code>int32 transcribedType = 4;</code>
     */
    public Builder setTranscribedType(int value) {

      transcribedType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 转写结果的类型，一个结果可以对应多的状态, 为以下状态按位与。
     *    TOKEN = 1;
     *    PUNCTUATION = 2;
     *    NUMBER = 4;
     *    PATCH = 8;
     *    DISFLUENCY = 16;
     * </pre>
     *
     * <code>int32 transcribedType = 4;</code>
     */
    public Builder clearTranscribedType() {

      transcribedType_ = 0;
      onChanged();
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


    // @@protoc_insertion_point(builder_scope:StreamingTranscriptionPiece)
  }

  // @@protoc_insertion_point(class_scope:StreamingTranscriptionPiece)
  private static final com.databaker.hyzx.speech.StreamingTranscriptionPiece DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.databaker.hyzx.speech.StreamingTranscriptionPiece();
  }

  public static com.databaker.hyzx.speech.StreamingTranscriptionPiece getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StreamingTranscriptionPiece>
      PARSER = new com.google.protobuf.AbstractParser<StreamingTranscriptionPiece>() {
    public StreamingTranscriptionPiece parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StreamingTranscriptionPiece(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StreamingTranscriptionPiece> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<StreamingTranscriptionPiece> getParserForType() {
    return PARSER;
  }

  public com.databaker.hyzx.speech.StreamingTranscriptionPiece getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
