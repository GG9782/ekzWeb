package com.ekz.ekzweb.domain.project.prj.jsonType;

import lombok.Data;

@Data
public class IssuePerToolingJsonType {
    private String name;
    private Integer toolingQuantity;
    private Integer issueTotal;
    private Integer issueOpen;
    private Integer issueClose;
    private Double issueToolingRate;
}
