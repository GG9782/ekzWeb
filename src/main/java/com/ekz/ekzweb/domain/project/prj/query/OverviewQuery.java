package com.ekz.ekzweb.domain.project.prj.query;


import lombok.Data;

import java.util.List;


@Data
public class OverviewQuery {

    private String prjCode;
    private String prjName;
    private Integer bu;
    private String customer;
    private String businessModel;
    private String coreInvest;
    private String productType;

    private List<String> leader;

    private Integer indicatorCost;
    private Integer indicatorSchedule;
    private Integer indicatorResource;
    private Integer indicatorQuality;

    private String currentStage;

}
