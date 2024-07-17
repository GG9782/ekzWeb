package com.ekz.ekzweb.domain.jsonType;


import lombok.Data;

@Data
public class IssueChartJsonType {
    private String stage;
    private Integer severity;
    private Integer openQuantity;
    private Integer CloseQuantity;
    private Double passRate;
}
