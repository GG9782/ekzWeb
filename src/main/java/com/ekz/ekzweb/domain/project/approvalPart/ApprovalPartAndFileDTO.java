package com.ekz.ekzweb.domain.project.approvalPart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ApprovalPartAndFileDTO {
    @TableId(type = IdType.ASSIGN_UUID)
    @Schema(description = "UUID，新增时不要有此参数")
    String id;
    String prjCode;
    String partNumber;
    String partName;
    String partType;
    Integer toolingStage;
    Integer faiTotal;
    Integer faiReject;
    String faiFileName;
    Integer cpkTotal;
    Integer cpkReject;
    String cpkFileName;
    String faiOrCpkOrBoth;
    MultipartFile file;
}
