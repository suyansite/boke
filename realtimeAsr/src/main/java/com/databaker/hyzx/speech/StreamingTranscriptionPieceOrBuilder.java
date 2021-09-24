// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

public interface StreamingTranscriptionPieceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:StreamingTranscriptionPiece)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 转写分解结果。
   * </pre>
   *
   * <code>string transcribedText = 1;</code>
   */
  String getTranscribedText();
  /**
   * <pre>
   * 转写分解结果。
   * </pre>
   *
   * <code>string transcribedText = 1;</code>
   */
  com.google.protobuf.ByteString
      getTranscribedTextBytes();

  /**
   * <pre>
   * 分解开始时间（音频开始时间为0）。
   * </pre>
   *
   * <code>int64 beginTimestamp = 2;</code>
   */
  long getBeginTimestamp();

  /**
   * <pre>
   * 分解结束时间（音频开始时间为0）。
   * </pre>
   *
   * <code>int64 endTimestamp = 3;</code>
   */
  long getEndTimestamp();

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
  int getTranscribedType();
}
