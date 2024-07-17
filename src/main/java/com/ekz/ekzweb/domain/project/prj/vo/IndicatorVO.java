package com.ekz.ekzweb.domain.project.prj.vo;

import com.ekz.ekzweb.domain.jsonType.StringAndStyleJsonType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IndicatorVO {

    private String prjCode;
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
    private List<StringAndStyleJsonType> indicatorUserDefine;
    private String indicatorUpdater;
    private LocalDateTime indicatorUpdateTime;

}
