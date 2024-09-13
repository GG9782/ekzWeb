package com.ekz.ekzweb.domain.project.approvalPart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Data
public class ApprovalPart {
    @TableId(type = IdType.ASSIGN_UUID)
    @Schema(description = "UUID，新增时不要有此参数")
    private String id;
    private String prjCode;
    private String partNumber;
    private String partName;
    private String vendor;
    private String customer;
    private String bu;
    private String partType;
    private Integer toolingStage;

    private Integer faiAccept;
    private Integer faiAlert;
    private Integer faiReject;
    private String faiFileName;
    private Integer cpkAccept;
    private Integer cpkAlert;
    private Integer cpkReject;
    private String cpkFileName;
    private String Creator;
    private LocalDateTime createTime;

    @Schema(description = "不用传，新增和修改时自动生成")
    private LocalDate itemDate;
    @Schema(description = "不用传，新增和修改时自动生成")
    private Integer itemYear;
    @Schema(description = "不用传，新增和修改时自动生成")
    private Month itemMonth;
}
