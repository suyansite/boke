package com.databaker.hyzx.constant;

import com.databaker.hyzx.entity.MeetingEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public interface Constants {

    //会议实例保存集合
    Map<String, MeetingEntity> MEETING_ENTITY_MAP = new ConcurrentHashMap<String, MeetingEntity>();
    //语气词
    List<String> STATEMENTONE = Arrays.asList("嗯。", "啊。", "哦。", "恩。", "一直。",
         "就是。", "所以。", "那样的。", "这样的。", "什么。", "大概。", "可能。", "然后。", "一个。", "那个。",
         "这个。", "这。", "那么个。", "这么个。","嗯嗯。","嗯嗯嗯。");

    //音频文件保存路径
   //String rootPath = "/newdata/ziyanfanew/tp5/public/mp3/";


    //会议默认自动保存/销毁等待时长
    Long suspendTime = 1000*60*5L;

     //依图应用key
    String DevKey = "OWNhOTNjYzk4NTg2NGM2ZGJhZjExZTY3MTM1NDZhNzI=";
    //依图应用id
    String DevId = "22009";
    //标贝应用id
    String CLIENT_ID = "57865a95-6c8e-4af6-9543-5214851dfc03";
    //标贝应用秘钥
    String CLIENT_SECRET = "MDhjMjg1YzEtYTNlYi00ZmZkLWI1ZGQtNmU2ZDZkZmJlZmNi";
    //标贝获取token地址
    String TOKEN_ADDRESS = "https://openapi.data-baker.com/oauth/2.0/token?grant_type=client_credentials&";

    Map<String,String> TOKEN_MAP = new ConcurrentHashMap<String,String>();
}
