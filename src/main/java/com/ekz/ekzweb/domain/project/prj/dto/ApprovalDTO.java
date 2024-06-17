package com.ekz.ekzweb.domain.project.prj.dto;

import com.ekz.ekzweb.domain.project.prj.jsonType.IssuePerToolingJsonType;
import com.ekz.ekzweb.domain.project.prj.jsonType.PassRateJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ApprovalDTO {

    private String prjCode;

    private PassRateJsonType gpm;

    private PassRateJsonType sa;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<PassRateJsonType> fai;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<PassRateJsonType> cpk;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<PassRateJsonType> partTest;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<PassRateJsonType> readyForApprovalMetal;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<PassRateJsonType> readyForApprovalPlastic;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<IssuePerToolingJsonType> issuePerTooling;

}