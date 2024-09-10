package com.ekz.ekzweb.domain.kpi;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Data
@TableName(autoResultMap = true)
public class MpIssue {

    @Schema(description = "UUID, 新增时自动生成")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private LocalDate itemDate;
    @Schema(description = "不用传，新增和修改时自动生成")
    private Integer itemYear;
    @Schema(description = "不用传，新增和修改时自动生成")
    private Month itemMonth;
    private String customer;
    private String type;
    private String description;
    private String status;

    private String creator;
    private LocalDateTime createTime;

}
