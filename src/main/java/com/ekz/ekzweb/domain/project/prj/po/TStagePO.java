package com.ekz.ekzweb.domain.project.prj.po;



import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "project", autoResultMap = true)
public class TStagePO {

    @TableId(type = IdType.INPUT)
    private String prjCode;
    @TableLogic
    private Integer deleted;
    private Integer tStageQuantity;
    private Integer dStageQuantity;
    private Boolean isTFinal;
//    @TableField(typeHandler = JacksonTypeHandler.class)
//    private String tStage;
    private String tStageUpdater;
    private LocalDateTime tStageUpdateTime;
}
