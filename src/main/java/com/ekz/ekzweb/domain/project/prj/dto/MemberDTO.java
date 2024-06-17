package com.ekz.ekzweb.domain.project.prj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MemberDTO {

    private String prjCode;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<String> leader;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<String>  meMember;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<String>  idMember;
    @Schema(description = "Json字段，需要整个一起处理")
    private List<String>  packingMember;

}
