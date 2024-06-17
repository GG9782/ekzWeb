package com.ekz.ekzweb.domain.project.prj.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName(value = "project", autoResultMap = true)
public class AttributePO {

    @TableId(type = IdType.INPUT)
    private String prjCode;
    @TableLogic
    private Integer deleted;
    private String prjName;
    private String bu;
    private String customer;
    private String businessModel;
    private String coreInvest;
    private String productType;
    private LocalDateTime createTime;
    private String creator;
    private LocalDateTime attributeUpdateTime;
    private String attributeUpdater;

}
