package com.ekz.ekzweb.domain.project.prj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
public class PartQuantityDTO {

    private String prjCode;
    @Schema(description = "Json字段，需要整个一起处理")
    private Map<String,Integer> metalPartQuantity;
    @Schema(description = "Json字段，需要整个一起处理")
    private Map<String,Integer> plasticPartQuantity;
    @Schema(description = "Json字段，需要整个一起处理")
    private Map<String,Integer> dieCastingPartQuantity;
}
