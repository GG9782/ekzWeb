package com.ekz.ekzweb.domain.project.prj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ekz.ekzweb.domain.project.prj.jsonType.PrjReadinessJsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "project", autoResultMap = true)
public class PrjReadinessPO {
    @TableId(type = IdType.INPUT)
    private String prjCode;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private List<PrjReadinessJsonType> prjReadiness;
    private String prjReadinessUpdater;
    private LocalDateTime prjReadinessUpdateTime;
}
