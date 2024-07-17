package com.ekz.ekzweb.domain.project.prj.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ekz.ekzweb.domain.jsonType.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Schema(description = "project")
@TableName(value = "project", autoResultMap = true)
public class Project {

    @TableId(type = IdType.INPUT)
    private String prjCode;


    private String prjName;

    private String department;
    @Schema(description = "逻辑删除，0逻辑未删除值，1逻辑已删除值，默认0")
    @TableLogic
    private Integer deleted;
    private String bu;
    private String customer;
    private String businessModel;
    private String coreInvest;
    private String productType;
    private LocalDateTime createTime;
    private String creator;
    private LocalDateTime attributeUpdateTime;
    private String attributeUpdater;

//    public void setCreateTime() {
//        this.createTime = LocalDateTime.now();
//    }
//
//    public void setAttributeUpdateTime() {
//        this.attributeUpdateTime = LocalDateTime.now();
//    }
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> leader;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String>  meMember;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String>  idMember;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String>  packingMember;
    private LocalDateTime memberUpdateTime;
    private String memberUpdater;


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
    @TableField(typeHandler = JacksonTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private List<StringAndStyleJsonType> indicatorUserDefine;
    private String indicatorUpdater;
    private LocalDateTime indicatorUpdateTime;

    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String,Integer> metalPartQuantity;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String,Integer> plasticPartQuantity;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String,Integer> dieCastingPartQuantity;
    private String partQuantityUpdater;
    private LocalDateTime partQuantityUpdateTime;

//    @TableField(typeHandler = JacksonTypeHandler.class, jdbcType = JdbcType.VARCHAR)
//    private List<String> tStage;
    private Integer tStageQuantity;
    private Boolean isTFinal;
    private Integer dStageQuantity;
    private String tStageUpdater;
    private LocalDateTime tStageUpdateTime;

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
    private List<PassRateJsonType> ppap3b;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PassRateJsonType> cpk;
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

    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ScheduleJsonType> schedule;

    private String scheduleUpdater;
    private LocalDateTime scheduleUpdateTime;

    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private List<PrjReadinessJsonType> prjReadiness;
    private String prjReadinessUpdater;
    private LocalDateTime prjReadinessUpdateTime;
}
