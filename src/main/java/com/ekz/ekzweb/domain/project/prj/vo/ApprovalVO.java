package com.ekz.ekzweb.domain.project.prj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ekz.ekzweb.domain.project.prj.jsonType.IssuePerToolingJsonType;
import com.ekz.ekzweb.domain.project.prj.jsonType.PassRateJsonType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApprovalVO {

    private String prjCode;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private PassRateJsonType gpm;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private PassRateJsonType sa;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> fai;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> cpk;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> partTest;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> readyForApprovalMetal;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> readyForApprovalPlastic;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<IssuePerToolingJsonType> issuePerTooling;

    private String saUpdater;
    private LocalDateTime gpmSaUpdateTime;

}