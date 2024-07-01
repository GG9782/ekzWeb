package com.ekz.ekzweb.domain.standardValue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StdReadiness {

    @TableId(type = IdType.AUTO)
    @Schema(description = "自增id，新增时不要有此参数")
    private Integer id;

    private String bu;
    private String customer;
    private String productType;

    private Integer item;
    private String deliverables;
    private String reviewFocus;

    private LocalDateTime createTime;
    private String creator;

}
