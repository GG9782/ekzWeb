package com.ekz.ekzweb.domain.project.prj.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ekz.ekzweb.domain.jsonType.IssuePerToolingJsonType;
import com.ekz.ekzweb.domain.jsonType.PassRateJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "project", autoResultMap = true)
public class ApprovalPO {

    @TableId(type = IdType.INPUT)
    private String prjCode;
    @TableLogic
    private Integer deleted;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private PassRateJsonType gpm;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private PassRateJsonType sa;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> fai;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> cpk;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> ppap3b;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> partTest;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> readyForApprovalMetal;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> readyForApprovalPlastic;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<IssuePerToolingJsonType> issuePerTooling;

    private String approvalUpdater;
    private LocalDateTime approvalUpdateTime;

}