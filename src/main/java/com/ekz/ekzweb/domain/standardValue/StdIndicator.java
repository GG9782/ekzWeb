package com.ekz.ekzweb.domain.standardValue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StdIndicator {

    @TableId(type = IdType.AUTO)
    @Schema(description = "自增id，新增时不要有此参数")
    private Integer id;
    private String name;
    private String customer;
    private String creator;
    private LocalDateTime createTime;

}
