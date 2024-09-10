package com.ekz.ekzweb.domain.project.approvalPart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApprovalPart {
    @TableId(type = IdType.ASSIGN_UUID)
    @Schema(description = "UUID，新增时不要有此参数")
    String id;
    String prjCode;
    String partNumber;
    String partName;
    String partType;
    Integer toolingStage;
    Integer faiAccept;
    Integer faiAlert;
    Integer faiReject;
    String faiFileName;
    Integer cpkAccept;
    Integer cpkAlert;
    Integer cpkReject;
    String cpkFileName;
    String Creator;
    private LocalDateTime createTime;
}
