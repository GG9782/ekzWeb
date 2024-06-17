package com.ekz.ekzweb.service.project.prj.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.dto.AttributeDTO;
import com.ekz.ekzweb.domain.project.prj.po.Project;
import com.ekz.ekzweb.mapper.project.prj.PrjMapper;
import com.ekz.ekzweb.service.project.prj.IPrjService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



@Service
public class PrjServiceImpl extends ServiceImpl<PrjMapper, Project> implements IPrjService {

    @Autowired
    private PrjMapper prjMapper;

    /** 全查 Overview*/
    @Override
    public List<Project> queryOverview(){
            List<Project>  list = lambdaQuery()
                .select(
                        Project::getPrjCode,
                        Project::getPrjName,
                        Project::getBu,
                        Project::getCustomer,
                        Project::getBusinessModel,
                        Project::getCoreInvest,
                        Project::getProductType,
                        Project::getLeader,
                        Project::getMeMember,
                        Project::getIdMember,
                        Project::getPackingMember,
                        Project::getIndicatorCost,
                        Project::getIndicatorSchedule,
                        Project::getIndicatorResource,
                        Project::getIndicatorQuality,
                        Project::getSchedule)
                .list();
        return list;

    }

//    /** 查 单个 Project Attribute*/
//    @Override
//    public Project getAttributeById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getPrjName,
//                        Project::getBu,
//                        Project::getCustomer,
//                        Project::getBusinessModel,
//                        Project::getCoreInvest,
//                        Project::getProductType,
//                        Project::getCreateTime,
//                        Project::getCreator,
//                        Project::getAttributeUpdateTime,
//                        Project::getAttributeUpdater)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }

    /** 复杂条件 查 多个 Attribute*/
    @Override
    public List<Project> queryPrjAttributeList(String prjCode, String prjName, String bu, String customer, String businessModel, String coreInvest, String coreInvest1, String productType, String creator, LocalDate earliestCreateDate, LocalDate latestCreateDate) {
        earliestCreateDate = earliestCreateDate != null ? earliestCreateDate : LocalDate.of(2000, 1, 1);
        latestCreateDate = latestCreateDate != null ? latestCreateDate : LocalDate.now().plusDays(1);
        System.out.println(earliestCreateDate);
        List<Project>  list = lambdaQuery()
                .select(
                        Project::getPrjCode,
                        Project::getPrjName,
                        Project::getBu,
                        Project::getCustomer,
                        Project::getBusinessModel,
                        Project::getCoreInvest,
                        Project::getProductType,
                        Project::getCreateTime,
                        Project::getCreator,
                        Project::getAttributeUpdateTime,
                        Project::getAttributeUpdater)
                .like(prjName  != null && !prjName.isEmpty(), Project::getPrjName, prjName )
                .eq( prjCode  != null && !prjCode.isEmpty(), Project::getPrjCode, prjCode )
                .eq( bu != null && !bu.isEmpty(), Project::getBu, bu)
                .like(customer != null && !customer.isEmpty(), Project::getCustomer, customer)
                .eq( businessModel != null && !businessModel.isEmpty(), Project::getBusinessModel, businessModel)
                .eq( coreInvest != null && !coreInvest.isEmpty(), Project::getCoreInvest, coreInvest)
                .eq( productType != null && !productType.isEmpty(), Project::getProductType, productType)
                .eq( creator != null && !creator.isEmpty(), Project::getCreator, creator)
                .between(Project::getCreateTime, earliestCreateDate.atStartOfDay(), latestCreateDate.plusDays(1).atStartOfDay())
                .list();
        return list;
    }

    /** 改 单个 Project Attribute*/
    @Override
    public void updateAttribute(String prjCode, AttributeDTO dto){
        Subject subject = SecurityUtils.getSubject();
        lambdaUpdate()
                .eq(Project::getPrjCode,prjCode)
                .set(dto.getPrjCode() != null && !dto.getPrjCode().isEmpty() , Project::getPrjCode,dto.getPrjCode())
                .set(dto.getPrjName() != null && !dto.getPrjName().isEmpty() , Project::getPrjName,dto.getPrjName())
                .set(dto.getBu() != null && !dto.getBu().isEmpty(), Project::getBu,dto.getBu())
                .set(dto.getCustomer() != null && !dto.getCustomer().isEmpty() , Project::getCustomer,dto.getCustomer())
                .set(dto.getBusinessModel() != null && !dto.getBusinessModel().isEmpty() , Project::getBusinessModel,dto.getBusinessModel())
                .set(dto.getCoreInvest() != null && !dto.getCoreInvest().isEmpty() , Project::getCoreInvest,dto.getCoreInvest())
                .set(dto.getProductType() != null && !dto.getProductType().isEmpty() , Project::getProductType,dto.getProductType())
                .set(Project::getAttributeUpdateTime,LocalDateTime.now())
                .set(Project::getAttributeUpdater,subject.getPrincipals().toString())
                .update();
    }

    /** 逻辑删除撤销 单个 Project Attribute*/
    @Override
    public void cancelLogicDeleteById(String prjCode) {
        prjMapper.cancelLogicDeleteById(prjCode);
    }

    /** 物理删除 单个 Project Attribute*/
    @Override
    public void physicsDeleteById(String prjCode){
        prjMapper.physicsDeleteById(prjCode);
    }

//    /** 查 单个 Project Indicator*/
//    @Override
//    public Project getIndicatorById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getIndicatorCost,
//                        Project::getIndicatorSchedule,
//                        Project::getIndicatorResource,
//                        Project::getIndicatorQuality,
//                        Project::getIndicatorMe,
//                        Project::getIndicatorPacking,
//                        Project::getIndicatorId,
//                        Project::getIndicatorThermal,
//                        Project::getIndicatorMaterial,
//                        Project::getIndicatorHousingDesign,
//                        Project::getIndicatorPcbDesign,
//                        Project::getIndicatorStructure,
//                        Project::getIndicatorUserDefine,
//                        Project::getIndicatorUpdater,
//                        Project::getIndicatorUpdateTime)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }
//
//    /** 改 单个 Project Indicator*/
//    @Override
//    public void updateIndicator(Project po) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,po.getPrjCode())
//                .set(po.getIndicatorCost() != null , Project::getIndicatorCost,po.getIndicatorCost())
//                .set(po.getIndicatorSchedule() != null , Project::getIndicatorSchedule,po.getIndicatorSchedule())
//                .set(po.getIndicatorResource() != null , Project::getIndicatorResource,po.getIndicatorResource())
//                .set(po.getIndicatorQuality() != null , Project::getIndicatorQuality,po.getIndicatorQuality())
//                .set(po.getIndicatorMe() != null , Project::getIndicatorMe,po.getIndicatorMe())
//                .set(po.getIndicatorPacking() != null , Project::getIndicatorPacking,po.getIndicatorPacking())
//                .set(po.getIndicatorId() != null , Project::getIndicatorId,po.getIndicatorId())
//                .set(po.getIndicatorThermal() != null , Project::getIndicatorThermal,po.getIndicatorThermal())
//                .set(po.getIndicatorMaterial() != null , Project::getIndicatorMaterial,po.getIndicatorMaterial())
//                .set(po.getIndicatorHousingDesign() != null , Project::getIndicatorHousingDesign,po.getIndicatorHousingDesign())
//                .set(po.getIndicatorPcbDesign() != null , Project::getIndicatorPcbDesign,po.getIndicatorPcbDesign())
//                .set(po.getIndicatorStructure() != null , Project::getIndicatorStructure,po.getIndicatorStructure())
//                .set(po.getIndicatorUserDefine() != null , Project::getIndicatorUserDefine,po.getIndicatorUserDefine())
//                .set(Project::getIndicatorUpdateTime,LocalDateTime.now())
//                .set(Project::getIndicatorUpdater,subject.getPrincipals())
//                .update();
//    }

//    /** 查 单个 Project Member*/
//    @Override
//    public Project getMemberById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getLeader,
//                        Project::getMeMember,
//                        Project::getIdMember,
//                        Project::getPackingMember,
//                        Project::getMemberUpdateTime,
//                        Project::getMemberUpdater)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }
//
//    /** 改 单个 Project Member*/
//    @Override
//    public void updateMember(MemberDTO dto) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,dto.getPrjCode())
//                .set(dto.getLeader() != null , Project::getLeader,dto.getLeader())
////                .set(dto.getMeMember() != null , Project::getMeMember,dto.getMeMember())
////                .set(dto.getIdMember() != null , Project::getIdMember,dto.getIdMember())
////                .set(dto.getPackingMember() != null , Project::getPackingMember,dto.getPackingMember())
////                .set(Project::getMemberUpdateTime,LocalDateTime.now())
////                .set(Project::getMemberUpdater,subject.getPrincipals())
//                .update();
//    }
//
//    /** 查 单个 Project Schedule*/
//    @Override
//    public Project getScheduleById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getSchedule,
//                        Project::getScheduleUpdater,
//                        Project::getScheduleUpdateTime)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }
//
//    /** 改 单个 Project Schedule*/
//    @Override
//    public void updateSchedule(String prjCode, ScheduleDTO dto) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,prjCode)
//                .set(dto.getSchedule() != null , Project::getSchedule,dto.getSchedule())
//                .set(Project::getScheduleUpdateTime,LocalDateTime.now())
//                .set(Project::getScheduleUpdater,subject.getPrincipals())
//                .update();
//    }
//
//    /** 查 单个 Project PartQuantity*/
//    @Override
//    public Project getPartQuantityById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getMetalPartQuantity,
//                        Project::getPlasticPartQuantity,
//                        Project::getPartQuantityUpdater,
//                        Project::getPartQuantityUpdateTime)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }
//
//    /** 改 单个 Project PartQuantity*/
//    @Override
//    public void updatePartQuantity(String prjCode, PartQuantityDTO dto) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,prjCode)
//                .set(dto.getMetalPartQuantity() != null , Project::getMetalPartQuantity,dto.getMetalPartQuantity())
//                .set(dto.getPlasticPartQuantity() != null , Project::getPlasticPartQuantity,dto.getPlasticPartQuantity())
//                .set(Project::getPartQuantityUpdateTime,LocalDateTime.now())
//                .set(Project::getPartQuantityUpdater,subject.getPrincipals())
//                .update();
//    }
//
//    /** 查 单个 Project TStage*/
//    @Override
//    public Project getTStageById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getTStage,
//                        Project::getTStageUpdater,
//                        Project::getTStageUpdateTime)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }
//    /** 改 单个 Project TStage*/
//    @Override
//    public void updateTStage(String prjCode, List<String> dto) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,prjCode)
//                .set(dto != null , Project::getTStage,dto)
//                .set(Project::getTStageUpdateTime,LocalDateTime.now())
//                .set(Project::getTStageUpdater,subject.getPrincipals())
//                .update();
//    }
//    @Override
//    public void updateTStage(String prjCode, TStageDTO dto) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,prjCode)
//                .set(dto.getTStage() != null , Project::getTStage,dto.getTStage())
//                .set(Project::getTStageUpdateTime,LocalDateTime.now())
//                .set(Project::getTStageUpdater,subject.getPrincipals())
//                .update();
//    }

//    /** 查 单个 Project Approval*/
//    @Override
//    public Project getApprovalById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getGpm,
//                        Project::getSa,
//                        Project::getFai,
//                        Project::getCpk,
//                        Project::getPartTest,
//                        Project::getReadyForApprovalMetal,
//                        Project::getReadyForApprovalPlastic,
//                        Project::getIssuePerTooling,
//                        Project::getApprovalUpdater,
//                        Project::getApprovalUpdateTime)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }
//    /** 改 单个 Project GpmSa*/
//    @Override
//    public void updateApproval(String prjCode, ApprovalDTO dto) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,prjCode)
//                .set(dto.getGpm() != null , Project::getGpm,dto.getGpm())
//                .set(dto.getSa() != null , Project::getSa,dto.getSa())
//                .set(dto.getFai() != null , Project::getFai,dto.getFai())
//                .set(dto.getCpk() != null , Project::getCpk,dto.getCpk())
//                .set(dto.getPartTest() != null , Project::getPartTest,dto.getPartTest())
//                .set(dto.getReadyForApprovalMetal() != null , Project::getReadyForApprovalMetal,dto.getReadyForApprovalMetal())
//                .set(dto.getReadyForApprovalPlastic() != null , Project::getReadyForApprovalPlastic,dto.getReadyForApprovalPlastic())
//                .set(Project::getApprovalUpdateTime,LocalDateTime.now())
//                .set(Project::getApprovalUpdater,subject.getPrincipals())
//                .update();
//    }
//
//    /** 查 单个 Project Issue */
//    @Override
//    public Project getIssueById(String prjCode) {
//        LambdaQueryWrapper<Project> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper
//                .select(
//                        Project::getPrjCode,
//                        Project::getIssue,
//                        Project::getIssueUpdater,
//                        Project::getIssueUpdateTime)
//                .eq(Project::getPrjCode, prjCode);
//        return prjMapper.selectOne(lambdaQueryWrapper);
//    }
//    /** 改 单个 Project Issue */
//    @Override
//    public void updateIssue(String prjCode, IssueDTO dto) {
//        Subject subject = SecurityUtils.getSubject();
//        lambdaUpdate()
//                .eq(Project::getPrjCode,prjCode)
//                .set(Project::getIssue,dto.getIssue())
//                .set(Project::getIssueUpdateTime,LocalDateTime.now())
//                .set(Project::getIssueUpdater,subject.getPrincipals())
//                .update();
//    }

}
