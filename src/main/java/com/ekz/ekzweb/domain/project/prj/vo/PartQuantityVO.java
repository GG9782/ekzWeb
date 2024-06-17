package com.ekz.ekzweb.domain.project.prj.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class PartQuantityVO {

    private String prjCode;
    private Map<String,Integer> metalPartQuantity;
    private Map<String,Integer> plasticPartQuantity;
    private Map<String,Integer> dieCastingPartQuantity;
    private String partQuantityUpdater;
    private LocalDateTime partQuantityUpdateTime;
}
