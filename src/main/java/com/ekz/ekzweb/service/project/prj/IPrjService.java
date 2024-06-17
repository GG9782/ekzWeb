package com.ekz.ekzweb.service.project.prj;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.project.prj.dto.AttributeDTO;
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

//    /** 查 单个 Project Indicator*/
//    Project getIndicatorById(String prjCode);
//
//    /** 改 单个 Project Indicator*/
//    void updateIndicator(Project po);
//
//    /** 查 单个 Project Member*/
//    Project getMemberById(String prjCode);
//
//    /** 改 单个 Project Member*/
//    void updateMember(MemberDTO po);
//
//    /** 查 单个 Project Schedule*/
//    Project getScheduleById(String prjCode);
//
//    /** 改 单个 Project Schedule*/
//    void updateSchedule(String prjCode, ScheduleDTO dto);
//
//    /** 查 单个 Project PartQuantity*/
//    Project getPartQuantityById(String prjCode);
//
//    /** 改 单个 Project PartQuantity*/
//    void updatePartQuantity(String prjCode, PartQuantityDTO dto);
//
//    /** 查 单个 Project TStage*/
//    Project getTStageById(String prjCode);
//
//    /** 改 单个 Project GpmSa*/
//
//    void updateTStage(String prjCode, TStageDTO dto);
//
//    /** 查 单个 Project GpmSa*/
//    Project getApprovalById(String prjCode);
//
//    /** 改 单个 Project GpmSa*/
//    void updateApproval(String prjCode, ApprovalDTO dto);
//
//
//    /** 查 单个 Project Issue*/
//    Project getIssueById(String prjCode);
//
//    /** 改 单个 Project Issue*/
//    void updateIssue(String prjCode, IssueDTO dto);


}
