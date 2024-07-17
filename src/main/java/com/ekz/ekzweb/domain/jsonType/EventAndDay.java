package com.ekz.ekzweb.domain.jsonType;

import lombok.Data;

import java.util.Map;

@Data
public class EventAndDay {
    private String eventName;
//    private String fontStyle;
    private Map<String,String> fontStyle;
    private String eventDay;
}
