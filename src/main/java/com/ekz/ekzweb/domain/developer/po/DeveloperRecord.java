package com.ekz.ekzweb.domain.developer.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DeveloperRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String layer;
    private String sublayer;
    private String description;
    private LocalDate finishDate;
    private String creator;
    private LocalDateTime createTime;
}
