package com.ekz.ekzweb.domain.kpi;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName(autoResultMap = true)
public class Tss {
    @Schema(description = "UUID, 新增时自动生成")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @Schema(description = "根据name补全")
    private String email;
    private String name;
    private String department;
    private String prjCode;
    private String prjName;
    private String taskItem;
    private LocalDate itemDate;
    @Schema(description = "YYYY-MM")
    private String itemYearMonth;
    private Double workingHour;
    private String description;
    private String tag;
    private String profitCenterDescription;
    @Schema(description = "根据profitCenterDescription补全")
    private String customer;
    @Schema(description = "根据profitCenterDescription补全")
    private String bu;
    @Schema(description = "补全:Non-project, R360, OTS, BUx")
    private String groupingRule1;
    @Schema(description = "根据TssAdditionalRules查询结果补全,无匹配则写入prjName")
    private String additionalPrjName;
}
