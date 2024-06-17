package com.ekz.ekzweb.domain.standardValue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(autoResultMap = true)
public class Stage {
    @TableId(type = IdType.AUTO)
    @Schema(description = "自增id，新增时不要有此参数")
    private Integer id;
    @Schema(description = "Json字段，需要整个一起处理")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> stage;
    private String customer;
    private String creator;
    private LocalDateTime createTime;
}
