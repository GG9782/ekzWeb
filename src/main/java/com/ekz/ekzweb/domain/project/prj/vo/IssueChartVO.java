package com.ekz.ekzweb.domain.project.prj.vo;


import com.ekz.ekzweb.domain.jsonType.IssueChartJsonType;
import lombok.Data;

import java.util.List;

@Data
public class IssueChartVO {
    private String prjCode;
    private List<IssueChartJsonType> issueChart;
}
