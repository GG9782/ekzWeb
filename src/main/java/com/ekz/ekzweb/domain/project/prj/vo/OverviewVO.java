package com.ekz.ekzweb.domain.project.prj.vo;


import com.ekz.ekzweb.domain.project.prj.jsonType.ScheduleJsonType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class OverviewVO {

    private String prjCode;
    private String prjName;
    private String bu;
    private String customer;
    private String businessModel;
    private String coreInvest;
    private String productType;

    private List<String> leader;
    private List<String> meMember;
    private List<String> idMember;
    private List<String> packingMember;

    private Integer indicatorCost;
    private Integer indicatorSchedule;
    private Integer indicatorResource;
    private Integer indicatorQuality;

    private String currentStage;

    public void setCurrentStage(List<ScheduleJsonType> schedule) {
        LocalDate today = LocalDate.now(); // 获取当前日期

        if (schedule == null) {
            currentStage = "无schedule";
            return;
        }

        LocalDate earliestStartDate = null;
        LocalDate latestEndDate = null;

        for (ScheduleJsonType stage : schedule) {
            LocalDate startDate = LocalDate.parse(stage.getStartDate());
            LocalDate endDate = LocalDate.parse(stage.getEndDate());

            if (today.isEqual(startDate) || today.isEqual(endDate) || (today.isAfter(startDate) && today.isBefore(endDate))) {
                currentStage = stage.getName();
                return;
            }

            if (earliestStartDate == null || startDate.isBefore(earliestStartDate)) {
                earliestStartDate = startDate;
            }

            if (latestEndDate == null || endDate.isAfter(latestEndDate)) {
                latestEndDate = endDate;
            }

        }
        if ( latestEndDate == null ){
            currentStage = "无schedule";
        }else if ( today.isAfter(latestEndDate) ) {
            currentStage = "已结束";
        }else if ( today.isBefore(earliestStartDate) ) {
            currentStage = "未开始";
        }else {
            currentStage = "空闲";
        }
    }

}
