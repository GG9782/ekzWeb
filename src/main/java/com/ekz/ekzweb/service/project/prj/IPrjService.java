package com.ekz.ekzweb.service.project.prj;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.project.prj.dto.AttributeDTO;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.project.prj.po.Project;

import java.time.LocalDate;
import java.util.List;


public interface IPrjService extends IService<Project> {

    /** 全查 Overview*/
    List<Project> queryOverview();

//    /** 查 单个 Project Attribute*/
//    Project getAttributeById(String prjCode);

    /** 复杂条件 查 多个 Attribute*/
    List<Project> queryPrjAttributeList(String prjCode, String prjName, String bu, String customer, String businessModel, String coreInvest, String coreInvest1, String productType, String creator, LocalDate earliestCreateDate, LocalDate latestCreateDate);

    /** 改 单个 Project Attribute*/
    void updateAttribute(String prjCode, AttributeDTO dto);

    /** 逻辑删除撤销 单个 Project Attribute*/
    void cancelLogicDeleteById(String prjCode);

    /** 物理删除 单个 Project Attribute*/
    void physicsDeleteById(String prjCode);

    List<AttributePO> getLogicDeleteProject();
}
