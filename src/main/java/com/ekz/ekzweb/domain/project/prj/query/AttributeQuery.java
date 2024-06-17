package com.ekz.ekzweb.domain.project.prj.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;


@Schema(description = "AttributeQuery")
@Data
public class AttributeQuery {
    private String prjCode;
    private String prjName;
    private String bu;
    private String customer;
    private String businessModel;
    private String coreInvest;
    private String productType;
    private String creator;
    @Schema(description = "earliestCreateDate和latestCreateDate要么都有，要么都没有，不能单有其中一个")
    private LocalDate earliestCreateDate;
    @Schema(description = "earliestCreateDate和latestCreateDate要么都有，要么都没有，不能单有其中一个")
    private LocalDate latestCreateDate;
}


