package com.ekz.ekzweb.domain.project.prj.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ekz.ekzweb.domain.project.prj.jsonType.StringAndStyleJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "project", autoResultMap = true)
public class IndicatorPO {

    @TableId(type = IdType.INPUT)
    private String prjCode;
    @TableLogic
    private Integer deleted;
    private Integer indicatorCost;
    private Integer indicatorSchedule;
    private Integer indicatorResource;
    private Integer indicatorQuality;
    private Integer indicatorMe;
    private Integer indicatorPacking;
    private Integer indicatorId;
    private Integer indicatorThermal;
    private Integer indicatorMaterial;
    private Integer indicatorHousingDesign;
    private Integer indicatorPcbDesign;
    private Integer indicatorStructure;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<StringAndStyleJsonType> indicatorUserDefine;
    private String indicatorUpdater;
    private LocalDateTime indicatorUpdateTime;

}
