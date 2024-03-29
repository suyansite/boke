// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

public interface StreamingTranscriptionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:StreamingTranscription)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 转写结果
   * </pre>
   *
   * <code>string transcribedText = 1;</code>
   */
  String getTranscribedText();
  /**
   * <pre>
   * 转写结果
   * </pre>
   *
   * <code>string transcribedText = 1;</code>
   */
  com.google.protobuf.ByteString
      getTranscribedTextBytes();

  /**
   * <pre>
   * 转写结果中包含热词的内容
   * </pre>
   *
   * <code>repeated .KeyWordsType keyWordsType = 2;</code>
   */
  java.util.List<com.databaker.hyzx.speech.KeyWordsType> 
      getKeyWordsTypeList();
  /**
   * <pre>
   * 转写结果中包含热词的内容
   * </pre>
   *
   * <code>repeated .KeyWordsType keyWordsType = 2;</code>
   */
  com.databaker.hyzx.speech.KeyWordsType getKeyWordsType(int index);
  /**
   * <pre>
   * 转写结果中包含热词的内容
   * </pre>
   *
   * <code>repeated .KeyWordsType keyWordsType = 2;</code>
   */
  int getKeyWordsTypeCount();
  /**
   * <pre>
   * 转写结果中包含热词的内容
   * </pre>
   *
   * <code>repeated .KeyWordsType keyWordsType = 2;</code>
   */
  java.util.List<? extends com.databaker.hyzx.speech.KeyWordsTypeOrBuilder> 
      getKeyWordsTypeOrBuilderList();
  /**
   * <pre>
   * 转写结果中包含热词的内容
   * </pre>
   *
   * <code>repeated .KeyWordsType keyWordsType = 2;</code>
   */
  com.databaker.hyzx.speech.KeyWordsTypeOrBuilder getKeyWordsTypeOrBuilder(
      int index);

  /**
   * <pre>
   * 转写结果的分解（只对final状态结果有效，返回每个字及标点的详细信息）
   * </pre>
   *
   * <code>repeated .StreamingTranscriptionPiece piece = 3;</code>
   */
  java.util.List<com.databaker.hyzx.speech.StreamingTranscriptionPiece> 
      getPieceList();
  /**
   * <pre>
   * 转写结果的分解（只对final状态结果有效，返回每个字及标点的详细信息）
   * </pre>
   *
   * <code>repeated .StreamingTranscriptionPiece piece = 3;</code>
   */
  com.databaker.hyzx.speech.StreamingTranscriptionPiece getPiece(int index);
  /**
   * <pre>
   * 转写结果的分解（只对final状态结果有效，返回每个字及标点的详细信息）
   * </pre>
   *
   * <code>repeated .StreamingTranscriptionPiece piece = 3;</code>
   */
  int getPieceCount();
  /**
   * <pre>
   * 转写结果的分解（只对final状态结果有效，返回每个字及标点的详细信息）
   * </pre>
   *
   * <code>repeated .StreamingTranscriptionPiece piece = 3;</code>
   */
  java.util.List<? extends com.databaker.hyzx.speech.StreamingTranscriptionPieceOrBuilder> 
      getPieceOrBuilderList();
  /**
   * <pre>
   * 转写结果的分解（只对final状态结果有效，返回每个字及标点的详细信息）
   * </pre>
   *
   * <code>repeated .StreamingTranscriptionPiece piece = 3;</code>
   */
  com.databaker.hyzx.speech.StreamingTranscriptionPieceOrBuilder getPieceOrBuilder(
      int index);
}
