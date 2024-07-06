package com.ekz.ekzweb.domain.project.prj.vo;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AttributeVO {

    private String prjCode;
    private String prjName;
    private String department;
    private String bu;
    private String customer;
    private String businessModel;
    private String coreInvest;
    private String productType;
    private LocalDateTime createTime;
    private String creator;
    private LocalDateTime attributeUpdateTime;
    private String attributeUpdater;

}
