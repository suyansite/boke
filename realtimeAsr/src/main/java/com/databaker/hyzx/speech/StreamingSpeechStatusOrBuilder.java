// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

public interface StreamingSpeechStatusOrBuilder extends
    // @@protoc_insertion_point(interface_extends:StreamingSpeechStatus)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 当前音频处理进行到的时间点（音频开始时间为0）
   * </pre>
   *
   * <code>int64 processedTimestamp = 1;</code>
   */
  long getProcessedTimestamp();
}