package com.ekz.ekzweb.domain.project.prj.po;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ekz.ekzweb.domain.project.prj.jsonType.ScheduleJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "project", autoResultMap = true)
public class SchedulePO {

    @TableId(type = IdType.INPUT)
    private String prjCode;
    @TableLogic
    private Integer deleted;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ScheduleJsonType> schedule;
    private String scheduleUpdater;
    private LocalDateTime scheduleUpdateTime;

}
