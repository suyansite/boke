// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

/**
 * <pre>
 *转写结果中包含关键词KeyWords的内容
 * </pre>
 *
 * Protobuf type {@code KeyWordsType}
 */
public  final class KeyWordsType extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:KeyWordsType)
    KeyWordsTypeOrBuilder {
private static final long serialVersionUID = 0L;
  // Use KeyWordsType.newBuilder() to construct.
  private KeyWordsType(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private KeyWordsType() {
    keyWords_ = "";
    keyWordsScore_ = 0F;
    startTimestamp_ = 0;
    endTimestamp_ = 0;
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private KeyWordsType(
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

            keyWords_ = s;
            break;
          }
          case 21: {

            keyWordsScore_ = input.readFloat();
            break;
          }
          case 24: {

            startTimestamp_ = input.readInt32();
            break;
          }
          case 32: {

            endTimestamp_ = input.readInt32();
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
    return com.databaker.hyzx.speech.StreamingProtos.internal_static_KeyWordsType_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.databaker.hyzx.speech.StreamingProtos.internal_static_KeyWordsType_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.databaker.hyzx.speech.KeyWordsType.class, com.databaker.hyzx.speech.KeyWordsType.Builder.class);
  }

  public static final int KEYWORDS_FIELD_NUMBER = 1;
  private volatile Object keyWords_;
  /**
   * <pre>
   * 命中的关键词KeyWords。返回不多于10个。
   * </pre>
   *
   * <code>string keyWords = 1;</code>
   */
  public String getKeyWords() {
    Object ref = keyWords_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      keyWords_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 命中的关键词KeyWords。返回不多于10个。
   * </pre>
   *
   * <code>string keyWords = 1;</code>
   */
  public com.google.protobuf.ByteString
      getKeyWordsBytes() {
    Object ref = keyWords_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      keyWords_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int KEYWORDSSCORE_FIELD_NUMBER = 2;
  private float keyWordsScore_;
  /**
   * <pre>
   * 命中的关键词KeyWords相应的分数。分数越高表示和关键词越相似，对应kws中的分数。
   * </pre>
   *
   * <code>float keyWordsScore = 2;</code>
   */
  public float getKeyWordsScore() {
    return keyWordsScore_;
  }

  public static final int STARTTIMESTAMP_FIELD_NUMBER = 3;
  private int startTimestamp_;
  /**
   * <pre>
   * 音频中对应的起始时间
   * </pre>
   *
   * <code>int32 startTimestamp = 3;</code>
   */
  public int getStartTimestamp() {
    return startTimestamp_;
  }

  public static final int ENDTIMESTAMP_FIELD_NUMBER = 4;
  private int endTimestamp_;
  /**
   * <pre>
   * 音频中对应的结束时间
   * </pre>
   *
   * <code>int32 endTimestamp = 4;</code>
   */
  public int getEndTimestamp() {
    return endTimestamp_;
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
    if (!getKeyWordsBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, keyWords_);
    }
    if (keyWordsScore_ != 0F) {
      output.writeFloat(2, keyWordsScore_);
    }
    if (startTimestamp_ != 0) {
      output.writeInt32(3, startTimestamp_);
    }
    if (endTimestamp_ != 0) {
      output.writeInt32(4, endTimestamp_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getKeyWordsBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, keyWords_);
    }
    if (keyWordsScore_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(2, keyWordsScore_);
    }
    if (startTimestamp_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, startTimestamp_);
    }
    if (endTimestamp_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, endTimestamp_);
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
    if (!(obj instanceof com.databaker.hyzx.speech.KeyWordsType)) {
      return super.equals(obj);
    }
    com.databaker.hyzx.speech.KeyWordsType other = (com.databaker.hyzx.speech.KeyWordsType) obj;

    boolean result = true;
    result = result && getKeyWords()
        .equals(other.getKeyWords());
    result = result && (
        Float.floatToIntBits(getKeyWordsScore())
        == Float.floatToIntBits(
            other.getKeyWordsScore()));
    result = result && (getStartTimestamp()
        == other.getStartTimestamp());
    result = result && (getEndTimestamp()
        == other.getEndTimestamp());
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
    hash = (37 * hash) + KEYWORDS_FIELD_NUMBER;
    hash = (53 * hash) + getKeyWords().hashCode();
    hash = (37 * hash) + KEYWORDSSCORE_FIELD_NUMBER;
    hash = (53 * hash) + Float.floatToIntBits(
        getKeyWordsScore());
    hash = (37 * hash) + STARTTIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + getStartTimestamp();
    hash = (37 * hash) + ENDTIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + getEndTimestamp();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.databaker.hyzx.speech.KeyWordsType parseFrom(
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
  public static Builder newBuilder(com.databaker.hyzx.speech.KeyWordsType prototype) {
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
   *转写结果中包含关键词KeyWords的内容
   * </pre>
   *
   * Protobuf type {@code KeyWordsType}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:KeyWordsType)
      com.databaker.hyzx.speech.KeyWordsTypeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_KeyWordsType_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_KeyWordsType_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.databaker.hyzx.speech.KeyWordsType.class, com.databaker.hyzx.speech.KeyWordsType.Builder.class);
    }

    // Construct using com.databaker.hyzx.speech.KeyWordsType.newBuilder()
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
      keyWords_ = "";

      keyWordsScore_ = 0F;

      startTimestamp_ = 0;

      endTimestamp_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.databaker.hyzx.speech.StreamingProtos.internal_static_KeyWordsType_descriptor;
    }

    public com.databaker.hyzx.speech.KeyWordsType getDefaultInstanceForType() {
      return com.databaker.hyzx.speech.KeyWordsType.getDefaultInstance();
    }

    public com.databaker.hyzx.speech.KeyWordsType build() {
      com.databaker.hyzx.speech.KeyWordsType result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.databaker.hyzx.speech.KeyWordsType buildPartial() {
      com.databaker.hyzx.speech.KeyWordsType result = new com.databaker.hyzx.speech.KeyWordsType(this);
      result.keyWords_ = keyWords_;
      result.keyWordsScore_ = keyWordsScore_;
      result.startTimestamp_ = startTimestamp_;
      result.endTimestamp_ = endTimestamp_;
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
      if (other instanceof com.databaker.hyzx.speech.KeyWordsType) {
        return mergeFrom((com.databaker.hyzx.speech.KeyWordsType)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.databaker.hyzx.speech.KeyWordsType other) {
      if (other == com.databaker.hyzx.speech.KeyWordsType.getDefaultInstance()) return this;
      if (!other.getKeyWords().isEmpty()) {
        keyWords_ = other.keyWords_;
        onChanged();
      }
      if (other.getKeyWordsScore() != 0F) {
        setKeyWordsScore(other.getKeyWordsScore());
      }
      if (other.getStartTimestamp() != 0) {
        setStartTimestamp(other.getStartTimestamp());
      }
      if (other.getEndTimestamp() != 0) {
        setEndTimestamp(other.getEndTimestamp());
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
      com.databaker.hyzx.speech.KeyWordsType parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.databaker.hyzx.speech.KeyWordsType) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object keyWords_ = "";
    /**
     * <pre>
     * 命中的关键词KeyWords。返回不多于10个。
     * </pre>
     *
     * <code>string keyWords = 1;</code>
     */
    public String getKeyWords() {
      Object ref = keyWords_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        keyWords_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <pre>
     * 命中的关键词KeyWords。返回不多于10个。
     * </pre>
     *
     * <code>string keyWords = 1;</code>
     */
    public com.google.protobuf.ByteString
        getKeyWordsBytes() {
      Object ref = keyWords_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        keyWords_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 命中的关键词KeyWords。返回不多于10个。
     * </pre>
     *
     * <code>string keyWords = 1;</code>
     */
    public Builder setKeyWords(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      keyWords_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 命中的关键词KeyWords。返回不多于10个。
     * </pre>
     *
     * <code>string keyWords = 1;</code>
     */
    public Builder clearKeyWords() {

      keyWords_ = getDefaultInstance().getKeyWords();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 命中的关键词KeyWords。返回不多于10个。
     * </pre>
     *
     * <code>string keyWords = 1;</code>
     */
    public Builder setKeyWordsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      keyWords_ = value;
      onChanged();
      return this;
    }

    private float keyWordsScore_ ;
    /**
     * <pre>
     * 命中的关键词KeyWords相应的分数。分数越高表示和关键词越相似，对应kws中的分数。
     * </pre>
     *
     * <code>float keyWordsScore = 2;</code>
     */
    public float getKeyWordsScore() {
      return keyWordsScore_;
    }
    /**
     * <pre>
     * 命中的关键词KeyWords相应的分数。分数越高表示和关键词越相似，对应kws中的分数。
     * </pre>
     *
     * <code>float keyWordsScore = 2;</code>
     */
    public Builder setKeyWordsScore(float value) {

      keyWordsScore_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 命中的关键词KeyWords相应的分数。分数越高表示和关键词越相似，对应kws中的分数。
     * </pre>
     *
     * <code>float keyWordsScore = 2;</code>
     */
    public Builder clearKeyWordsScore() {

      keyWordsScore_ = 0F;
      onChanged();
      return this;
    }

    private int startTimestamp_ ;
    /**
     * <pre>
     * 音频中对应的起始时间
     * </pre>
     *
     * <code>int32 startTimestamp = 3;</code>
     */
    public int getStartTimestamp() {
      return startTimestamp_;
    }
    /**
     * <pre>
     * 音频中对应的起始时间
     * </pre>
     *
     * <code>int32 startTimestamp = 3;</code>
     */
    public Builder setStartTimestamp(int value) {

      startTimestamp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 音频中对应的起始时间
     * </pre>
     *
     * <code>int32 startTimestamp = 3;</code>
     */
    public Builder clearStartTimestamp() {

      startTimestamp_ = 0;
      onChanged();
      return this;
    }

    private int endTimestamp_ ;
    /**
     * <pre>
     * 音频中对应的结束时间
     * </pre>
     *
     * <code>int32 endTimestamp = 4;</code>
     */
    public int getEndTimestamp() {
      return endTimestamp_;
    }
    /**
     * <pre>
     * 音频中对应的结束时间
     * </pre>
     *
     * <code>int32 endTimestamp = 4;</code>
     */
    public Builder setEndTimestamp(int value) {

      endTimestamp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 音频中对应的结束时间
     * </pre>
     *
     * <code>int32 endTimestamp = 4;</code>
     */
    public Builder clearEndTimestamp() {

      endTimestamp_ = 0;
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


    // @@protoc_insertion_point(builder_scope:KeyWordsType)
  }

  // @@protoc_insertion_point(class_scope:KeyWordsType)
  private static final com.databaker.hyzx.speech.KeyWordsType DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.databaker.hyzx.speech.KeyWordsType();
  }

  public static com.databaker.hyzx.speech.KeyWordsType getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<KeyWordsType>
      PARSER = new com.google.protobuf.AbstractParser<KeyWordsType>() {
    public KeyWordsType parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new KeyWordsType(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<KeyWordsType> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<KeyWordsType> getParserForType() {
    return PARSER;
  }

  public com.databaker.hyzx.speech.KeyWordsType getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

