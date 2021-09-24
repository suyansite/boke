package com.databaker.hyzx.utils;

import com.google.gson.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertJsonUtils {

    public static String convertToDataBakerJson(String str,String trace_id){
        try {
            JsonObject result = new JsonObject();
            JsonElement je = new JsonParser().parse(str);
            JsonObject jsonObject =  je.getAsJsonObject().getAsJsonObject("bestTranscription");
            String text = jsonObject.getAsJsonPrimitive("transcribedText").toString();
            JsonPrimitive isFinalJson = je.getAsJsonObject().getAsJsonPrimitive("isFinal");
            String isFinal = "false";
            if(isFinalJson != null){
                isFinal = "true";
            }
            result.addProperty("code",90000);
            result.addProperty("trace_id",trace_id);
            result.addProperty("asr_text",text.substring(1,text.length()-1));
            result.addProperty("sentence_end",isFinal);
            JsonArray piece = jsonObject.getAsJsonArray("piece");
            Long sosL = Long.valueOf(piece.get(0).getAsJsonObject().get("beginTimestamp").toString());
            Long eosL = Long.valueOf(piece.get(piece.size()-1).getAsJsonObject().get("endTimestamp").toString());
            java.text.DecimalFormat  df =  new java.text.DecimalFormat("#.00");
            Double sos = Double.valueOf(df.format(sosL/1000.0));
            Double eos = Double.valueOf(df.format(eosL/1000.0));
            result.addProperty("sos",sos);
            result.addProperty("eos",eos);
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
}
