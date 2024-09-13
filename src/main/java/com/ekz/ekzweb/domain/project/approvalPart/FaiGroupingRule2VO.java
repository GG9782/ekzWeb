package com.ekz.ekzweb.domain.project.approvalPart;

import lombok.Data;

@Data
public class FaiGroupingRule2VO {
    private Integer itemYear;
    private String customer;
    private String prjCode;
    private String vendor;
    private String partType;
    private Integer toolingStage;
    private String bu;

    private Integer t1SumFaiAccept;
    private Integer t1SumFaiAlert;
    private Integer t1SumFaiReject;

    private Integer t2SumFaiAccept;
    private Integer t2SumFaiAlert;
    private Integer t2SumFaiReject;

    private Integer t3SumFaiAccept;
    private Integer t3SumFaiAlert;
    private Integer t3SumFaiReject;

    private Integer t4SumFaiAccept;
    private Integer t4SumFaiAlert;
    private Integer t4SumFaiReject;
}
