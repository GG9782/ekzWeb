package com.ekz.ekzweb.domain.jsonType;

import lombok.Data;

@Data
public class PassRateJsonType {
    private String name;
    private Integer total;
    private Integer pass;
    private Integer fail;
    private Double passRate;
}
