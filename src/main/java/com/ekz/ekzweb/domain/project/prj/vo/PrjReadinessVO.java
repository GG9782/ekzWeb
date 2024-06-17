package com.ekz.ekzweb.domain.project.prj.vo;

import com.ekz.ekzweb.domain.project.prj.jsonType.PrjReadinessJsonType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrjReadinessVO {
    private String prjCode;
    private List<PrjReadinessJsonType> prjReadiness;
    private String prjReadinessUpdater;
    private LocalDateTime prjReadinessUpdateTime;
}
