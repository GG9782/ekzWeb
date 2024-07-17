package com.ekz.ekzweb.domain.jsonType;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleJsonType {
    private String name;
    private String color;
    private String startDate;
    private String endDate;
    private List<EventAndDay> events;
    private List<EventAndDay> subEvents;
}
