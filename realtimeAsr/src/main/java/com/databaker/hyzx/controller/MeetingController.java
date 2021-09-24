package com.databaker.hyzx.controller;


import com.databaker.hyzx.entity.MeetingEntity;
import com.databaker.hyzx.utils.R;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.databaker.hyzx.constant.Constants.MEETING_ENTITY_MAP;
import static com.databaker.hyzx.constant.Constants.suspendTime;


@AllArgsConstructor
@RestController
@RequestMapping("/meeting")
public class MeetingController {

    private static Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @GetMapping("/getMeetingProcess")
    public Object getMeetingProcess() {

        List<String> needSaveIds = new ArrayList<String>();

        List<String> underwayIds = new ArrayList<String>();

        List<String> needDeleteIds = new ArrayList<String>();
        Map<String,List<String>> result = new HashMap<String,List<String>>();
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
            if (meetingEntity.getIsFinal() == 0
                    && (System.currentTimeMillis() - meetingEntity.getLastTimeData()) < suspendTime
            ) {
                underwayIds.add(entry.getKey());
            }
            result.put("needDeleteIds",needDeleteIds);
            result.put("needSaveIds",needSaveIds);
            result.put("underwayIds",underwayIds);
        }
        return  R.ok().put("data",result);
    }

}