package com.ekz.ekzweb.domain.project.prj.vo;

import com.ekz.ekzweb.domain.jsonType.StringAndStyleJsonType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IndicatorVO {

    private String prjCode;
    private List<StringAndStyleJsonType> indicatorUserDefine;
    private String indicatorUpdater;
    private LocalDateTime indicatorUpdateTime;

}
