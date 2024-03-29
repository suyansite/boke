// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: real_time_asr.proto

package com.databaker.hyzx.speech;

public final class StreamingProtos {
  private StreamingProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AudioConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AudioConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SpeechConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SpeechConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_WordsReplace_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_WordsReplace_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StreamingSpeechConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StreamingSpeechConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StreamingSpeechRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StreamingSpeechRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StreamingSpeechResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StreamingSpeechResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StreamingSpeechResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StreamingSpeechResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StreamingSpeechStatus_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StreamingSpeechStatus_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StreamingTranscription_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StreamingTranscription_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_KeyWordsType_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_KeyWordsType_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StreamingTranscriptionPiece_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StreamingTranscriptionPiece_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\023real_time_asr.proto\"\211\001\n\013AudioConfig\022\'\n" +
      "\003aue\030\001 \001(\0162\032.AudioConfig.AudioEncoding\022\022" +
      "\n\nsampleRate\030\002 \001(\005\"=\n\rAudioEncoding\022\017\n\013U" +
      "NSPECIFIED\020\000\022\007\n\003PCM\020\001\022\007\n\003AAC\020\002\022\t\n\005MPEG2\020" +
      "\003\"\266\003\n\014SpeechConfig\022$\n\004lang\030\001 \001(\0162\026.Speec" +
      "hConfig.Language\022\"\n\005scene\030\002 \001(\0162\023.Speech" +
      "Config.Scene\022\022\n\ncustomWord\030\003 \003(\t\022\030\n\020useC" +
      "ustomWordsId\030\004 \003(\005\0222\n\rrecognizeType\030\005 \001(" +
      "\0162\033.SpeechConfig.RecognizeType\022\034\n\024disabl" +
      "eConvertNumber\030\006 \001(\010\022\032\n\022disablePunctuati" +
      "on\030\007 \001(\010\022#\n\014wordsReplace\030\010 \001(\0132\r.WordsRe" +
      "place\022\020\n\010keyWords\030\t \003(\t\"6\n\010Language\022\017\n\013U" +
      "NSPECIFIED\020\000\022\014\n\010MANDARIN\020\001\022\013\n\007ENGLISH\020\002\"" +
      "\031\n\005Scene\022\020\n\014GENERALSCENE\020\000\"6\n\rRecognizeT" +
      "ype\022\007\n\003ALL\020\000\022\r\n\tUTTERANCE\020\001\022\r\n\tSTREAMING" +
      "\020\002\"1\n\014WordsReplace\022\020\n\010keywords\030\001 \003(\t\022\017\n\007" +
      "replace\030\002 \003(\t\"_\n\025StreamingSpeechConfig\022!" +
      "\n\013audioConfig\030\001 \001(\0132\014.AudioConfig\022#\n\014spe" +
      "echConfig\030\002 \001(\0132\r.SpeechConfig\"x\n\026Stream" +
      "ingSpeechRequest\0227\n\025streamingSpeechConfi" +
      "g\030\001 \001(\0132\026.StreamingSpeechConfigH\000\022\023\n\taud" +
      "ioData\030\002 \001(\014H\000B\020\n\016requestPayload\"\201\001\n\027Str" +
      "eamingSpeechResponse\022\026\n\016globalStreamId\030\001" +
      " \001(\t\022&\n\006result\030\002 \001(\0132\026.StreamingSpeechRe" +
      "sult\022&\n\006status\030\003 \001(\0132\026.StreamingSpeechSt" +
      "atus\"\\\n\025StreamingSpeechResult\022\017\n\007isFinal" +
      "\030\001 \001(\010\0222\n\021bestTranscription\030\002 \001(\0132\027.Stre" +
      "amingTranscription\"3\n\025StreamingSpeechSta" +
      "tus\022\032\n\022processedTimestamp\030\001 \001(\003\"\203\001\n\026Stre" +
      "amingTranscription\022\027\n\017transcribedText\030\001 " +
      "\001(\t\022#\n\014keyWordsType\030\002 \003(\0132\r.KeyWordsType" +
      "\022+\n\005piece\030\003 \003(\0132\034.StreamingTranscription" +
      "Piece\"e\n\014KeyWordsType\022\020\n\010keyWords\030\001 \001(\t\022" +
      "\025\n\rkeyWordsScore\030\002 \001(\002\022\026\n\016startTimestamp" +
      "\030\003 \001(\005\022\024\n\014endTimestamp\030\004 \001(\005\"}\n\033Streamin" +
      "gTranscriptionPiece\022\027\n\017transcribedText\030\001" +
      " \001(\t\022\026\n\016beginTimestamp\030\002 \001(\003\022\024\n\014endTimes" +
      "tamp\030\003 \001(\003\022\027\n\017transcribedType\030\004 \001(\0052]\n\021S" +
      "peechRecognition\022H\n\017RecognizeStream\022\027.St" +
      "reamingSpeechRequest\032\030.StreamingSpeechRe" +
      "sponse(\0010\001B(\n\023com.yitutech.speechB\017Strea" +
      "mingProtosP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_AudioConfig_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AudioConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AudioConfig_descriptor,
        new String[] { "Aue", "SampleRate", });
    internal_static_SpeechConfig_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_SpeechConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SpeechConfig_descriptor,
        new String[] { "Lang", "Scene", "CustomWord", "UseCustomWordsId", "RecognizeType", "DisableConvertNumber", "DisablePunctuation", "WordsReplace", "KeyWords", });
    internal_static_WordsReplace_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_WordsReplace_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_WordsReplace_descriptor,
        new String[] { "Keywords", "Replace", });
    internal_static_StreamingSpeechConfig_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_StreamingSpeechConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StreamingSpeechConfig_descriptor,
        new String[] { "AudioConfig", "SpeechConfig", });
    internal_static_StreamingSpeechRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_StreamingSpeechRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StreamingSpeechRequest_descriptor,
        new String[] { "StreamingSpeechConfig", "AudioData", "RequestPayload", });
    internal_static_StreamingSpeechResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_StreamingSpeechResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StreamingSpeechResponse_descriptor,
        new String[] { "GlobalStreamId", "Result", "Status", });
    internal_static_StreamingSpeechResult_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_StreamingSpeechResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StreamingSpeechResult_descriptor,
        new String[] { "IsFinal", "BestTranscription", });
    internal_static_StreamingSpeechStatus_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_StreamingSpeechStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StreamingSpeechStatus_descriptor,
        new String[] { "ProcessedTimestamp", });
    internal_static_StreamingTranscription_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_StreamingTranscription_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StreamingTranscription_descriptor,
        new String[] { "TranscribedText", "KeyWordsType", "Piece", });
    internal_static_KeyWordsType_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_KeyWordsType_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_KeyWordsType_descriptor,
        new String[] { "KeyWords", "KeyWordsScore", "StartTimestamp", "EndTimestamp", });
    internal_static_StreamingTranscriptionPiece_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_StreamingTranscriptionPiece_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StreamingTranscriptionPiece_descriptor,
        new String[] { "TranscribedText", "BeginTimestamp", "EndTimestamp", "TranscribedType", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
