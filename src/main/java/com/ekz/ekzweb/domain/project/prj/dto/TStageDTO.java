package com.ekz.ekzweb.domain.project.prj.dto;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

@Data
@TableName(value = "project", autoResultMap = true)
public class TStageDTO {
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String prjCode;
    private Boolean isTFinal;
//    @TableField(typeHandler = JacksonTypeHandler.class)
//    private List<String> tStage;
    private Integer tStageQuantity;
    private Integer dStageQuantity;
}
