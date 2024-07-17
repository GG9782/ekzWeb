package com.ekz.ekzweb.domain.project.prj.dto;

import com.ekz.ekzweb.domain.jsonType.PrjReadinessJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class PrjReadinessDTO {
    private String prjCode;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<PrjReadinessJsonType> prjReadiness;
}
