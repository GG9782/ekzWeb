package com.ekz.ekzweb.domain.project.textHighLowLight;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TextHighLowLight {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String prjCode;
    private String description;
    private String action;
    private String status;
    private String remark;
    private String creator;
    private LocalDateTime createTime;

}
