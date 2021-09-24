package com.databaker.hyzx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "asr")
public class PropertiesConfig {

    private String databaker_realtime_url;
    //音频文件根路径
    private String rootPath;
    //热词查询接口地址
    private String hotWordUrl;

}
