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
public class BusinessTrip {

    @Schema(description = "UUID, 新增时自动生成")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String email;
    private String department;
    private String name;
    private String cnName;
    private String jobTitle;

    private String bu;
    private String customer;
    private String prjCode;
    private String prjName;

    private String location;
    private String region;

    private String purpose;

    private LocalDate startDate;
    private LocalDate endDate;

    @Schema(description = "不用传，新增和修改时自动生成")
    private Integer itemYear;
    @Schema(description = "不用传，新增和修改时自动生成")
    private Month itemMonth;
    @Schema(description = "不用传，新增和修改时自动生成")
    private Integer days;

    private String creator;
    private LocalDateTime createTime;

}
