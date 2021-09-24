package com.databaker.hyzx.task;

import com.databaker.hyzx.entity.MeetingEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.databaker.hyzx.constant.Constants.*;
import static com.databaker.hyzx.utils.TokenUtils.getToken;

@AllArgsConstructor
@Component
public class AutoTask {

    private static Logger logger = LoggerFactory.getLogger(AutoTask.class);

    @Scheduled(cron = "0 */3 * * * ?")//每3分钟执行一次
    public  void refreshData() {

        List<String> needSaveIds = new ArrayList<String>();
        List<String> needDeleteIds = new ArrayList<String>();
        logger.info("自动任务执行");
        for (Map.Entry<String, MeetingEntity> entry : MEETING_ENTITY_MAP.entrySet()) {

            MeetingEntity meetingEntity = entry.getValue();
            if (meetingEntity.getIsFinal() == 0
                    && StringUtils.isEmpty(meetingEntity.getOpenid())
                    && (System.currentTimeMillis() - meetingEntity.getLastTimeData()) > suspendTime
            ) {
                meetingEntity.setIsFinal(2);
                needDeleteIds.add(entry.getKey());
            }

            if (meetingEntity.getIsFinal() == 0
                    && !StringUtils.isEmpty(meetingEntity.getOpenid())
                    && (System.currentTimeMillis() - meetingEntity.getLastTimeData()) > suspendTime
            ) {
                needSaveIds.add(entry.getKey());
            }
        }
        if (!CollectionUtils.isEmpty(needDeleteIds)) {
            for (int i = 0; i < needDeleteIds.size(); i++) {
                MEETING_ENTITY_MAP.remove(needDeleteIds.get(i));
                logger.info("自动任务删除会议{}完成",needDeleteIds.get(i));
            }
        }

        if (!CollectionUtils.isEmpty(needSaveIds)) {
            for (int i = 0; i < needSaveIds.size(); i++) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MEETING_ENTITY_MAP.get(needSaveIds.get(i)).savData();
                logger.info("自动任务保存会议{}完成",needSaveIds.get(i));
                MEETING_ENTITY_MAP.remove(needSaveIds.get(i));
            }
        }
    }

    @PostConstruct
    @Scheduled(cron = "0 0 4 * * ?")//每天凌晨4点执行
    public  void execute(){

        String token = getToken();
        //TOKEN_MAP.put("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiKiJdLCJzY29wZSI6WyJhc3IiXSwiZXhwIjoxNjMxOTA4ODAwLCJhdXRob3JpdGllcyI6WyIqIl0sImp0aSI6IjIyMzEzZDgwLTYwY2QtNDFhMC05MDM1LWNmZTNiOGQxOWIzOCIsImNsaWVudF9pZCI6IjU3ODY1YTk1LTZjOGUtNGFmNi05NTQzLTUyMTQ4NTFkZmMwMyJ9.-P9voX5zEZvHkjd0cfOOsRh-B5wKRGXZPChvyiLw2O0");
        TOKEN_MAP.put("token",token);
        logger.info("token={}",token);

    }
}