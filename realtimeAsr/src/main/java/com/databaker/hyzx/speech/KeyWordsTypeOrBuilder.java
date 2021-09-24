// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

public interface KeyWordsTypeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:KeyWordsType)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 命中的关键词KeyWords。返回不多于10个。
   * </pre>
   *
   * <code>string keyWords = 1;</code>
   */
  String getKeyWords();
  /**
   * <pre>
   * 命中的关键词KeyWords。返回不多于10个。
   * </pre>
   *
   * <code>string keyWords = 1;</code>
   */
  com.google.protobuf.ByteString
      getKeyWordsBytes();

  /**
   * <pre>
   * 命中的关键词KeyWords相应的分数。分数越高表示和关键词越相似，对应kws中的分数。
   * </pre>
   *
   * <code>float keyWordsScore = 2;</code>
   */
  float getKeyWordsScore();

  /**
   * <pre>
   * 音频中对应的起始时间
   * </pre>
   *
   * <code>int32 startTimestamp = 3;</code>
   */
  int getStartTimestamp();

  /**
   * <pre>
   * 音频中对应的结束时间
   * </pre>
   *
   * <code>int32 endTimestamp = 4;</code>
   */
  int getEndTimestamp();
}
