package com.ekz.ekzweb.domain.project.prj.jsonType;

import lombok.Data;

@Data
public class PrjReadinessJsonType {
    private String dueDate;
    private Double finishRate;
    private String remark;
    private Integer item;
}
