package com.ekz.ekzweb.domain.project.prj.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@TableName(value = "project", autoResultMap = true)
public class PartQuantityPO {

    @TableId(type = IdType.INPUT)
    private String prjCode;
    @TableLogic
    private Integer deleted;
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
}
