package com.ekz.ekzweb.domain.project.prj.dto;

import com.ekz.ekzweb.domain.jsonType.StringAndStyleJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class IndicatorDTO {

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
    @Schema(description = "Json字段，需要整个一起处理")
    private List<StringAndStyleJsonType> indicatorUserDefine;

}
