package com.ekz.ekzweb.domain.project.prj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "project", autoResultMap = true)
public class NoteTextPO {
    @TableId(type = IdType.INPUT)
    private String prjCode;
    private String noteText;
    private String noteUpdater;
    private LocalDateTime noteUpdateTime;
}
