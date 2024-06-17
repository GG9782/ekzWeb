package com.ekz.ekzweb.domain.project.prj.vo;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TStageVO {

    private String prjCode;
    private Integer tStageQuantity;
    private Integer dStageQuantity;
    private Boolean isTFinal;
//    private List<String> tStage;
    private String tStageUpdater;
    private LocalDateTime tStageUpdateTime;
}
