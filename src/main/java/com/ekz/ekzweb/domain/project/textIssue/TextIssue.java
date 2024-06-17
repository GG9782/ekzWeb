package com.ekz.ekzweb.domain.project.textIssue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName(autoResultMap = true)
public class TextIssue {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableLogic
    private Integer deleted;
    private String prjCode;
    private Boolean isTop;
    private Integer ranking;
    private String stage;
    private Integer severity;
    private String description;
    private String action;
    private String solution;
    private String pic;
    private String status;
    private LocalDate dueDate;
    private String report;
    private LocalDateTime createTime;
    private String creator;
}
