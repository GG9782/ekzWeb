package com.ekz.ekzweb.domain.kpi;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName(autoResultMap = true)
public class TssAdditionalRules {
    @Schema(description = "UUID, 新增时自动生成")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String prjCode;
    private String descriptionLike;
    private String additionalPrjName;
}
