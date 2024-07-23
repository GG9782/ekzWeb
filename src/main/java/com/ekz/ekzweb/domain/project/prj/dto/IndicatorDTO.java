package com.ekz.ekzweb.domain.project.prj.dto;

import com.ekz.ekzweb.domain.jsonType.StringAndStyleJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class IndicatorDTO {

    private String prjCode;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<StringAndStyleJsonType> indicatorUserDefine;

}
