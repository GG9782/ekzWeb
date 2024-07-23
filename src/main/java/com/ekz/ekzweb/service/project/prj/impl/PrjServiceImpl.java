package com.ekz.ekzweb.service.project.prj.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.dto.AttributeDTO;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
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
                        Project::getDepartment,
                        Project::getBusinessModel,
                        Project::getCoreInvest,
                        Project::getProductType,
                        Project::getLeader,
                        Project::getMeMember,
                        Project::getIdMember,
                        Project::getPackingMember,
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

    @Override
    public List<AttributePO> getLogicDeleteProject(){return prjMapper.getLogicDeleteProject();}

}
