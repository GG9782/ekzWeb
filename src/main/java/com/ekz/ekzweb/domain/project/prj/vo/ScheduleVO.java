package com.ekz.ekzweb.domain.project.prj.vo;


import com.ekz.ekzweb.domain.jsonType.ScheduleJsonType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScheduleVO {

    private String prjCode;
    private List<ScheduleJsonType> schedule;
    private String scheduleUpdater;
    private LocalDateTime scheduleUpdateTime;
}
