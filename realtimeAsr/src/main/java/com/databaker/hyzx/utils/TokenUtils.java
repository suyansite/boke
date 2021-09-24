package com.databaker.hyzx.utils;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import static com.databaker.hyzx.constant.Constants.*;


public class TokenUtils {

    private static Logger logger = LoggerFactory.getLogger(TokenUtils.class);




    public static String getJsonStr() {


        String path = "C:\\Users\\admin\\Desktop\\会议室\\12.json";

        ArrayList<String> list = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br = null;
        try{
            File f = new File(path);
            fr = new FileReader(f); //fr对象 对应f的读出
            br = new BufferedReader(fr);//br是 对应fr的 读出缓冲区
            String shuchu=null;
            int i=0;
            String str ="";
            while((shuchu=br.readLine())!=null){
                list.add(shuchu);//用一个数组全部存起来方便修改
            }
            for(int j=0;j<list.size();j++){
                str = str+list.get(j);
            }
           return  str;


        }catch(Exception ex){
            ex.printStackTrace();
        }finally {

        }
        return  null;
    }

    public static String getToken() {

        String url = TOKEN_ADDRESS+"client_secret="+CLIENT_SECRET+"&client_id="+CLIENT_ID;
        String tokenStr = HttpUtils.sendGet(url);
        Gson gson = new Gson();
        Map map = gson.fromJson(tokenStr,Map.class);
        Object tokenObj = map.get("access_token");
        String token = "";
        if(tokenObj!=null){
            token = tokenObj.toString();
        }
        // logger.info("token="+token);
        return  token;
    }
}
