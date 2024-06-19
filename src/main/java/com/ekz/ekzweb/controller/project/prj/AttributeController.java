package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.AttributeDTO;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.project.prj.po.Project;
import com.ekz.ekzweb.domain.project.prj.query.AttributeQuery;
import com.ekz.ekzweb.domain.project.prj.vo.AttributeVO;
import com.ekz.ekzweb.service.project.prj.IAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Project attribute接口")
@RestController
@RequestMapping("/prj/attribute")
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;


/** Project Attribute*/

    /** 查 单个 Attribute*/
    @Operation(summary = "01.03.1 查 单个 Attribute")
    @GetMapping("/{prjCode}")
    public AttributeVO getAttributeById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( attributeService.getById(prjCode) ,AttributeVO.class);
    }

    /** 复杂条件 查 多个 Attribute*/
    @Operation(summary = "复杂条件 查 多个 Attribute")
    @GetMapping("/list")
    public List<AttributeVO> queryPrjAttributeList(AttributeQuery query) {
        List<AttributePO> poList = attributeService.lambdaQuery()
                .eq(query.getPrjCode() != null,AttributePO::getPrjCode,query.getPrjCode())
                .like(query.getPrjName() != null,AttributePO::getPrjName,query.getPrjName())
                .eq(query.getBu() != null,AttributePO::getBu,query.getBu())
                .eq(query.getCustomer() != null,AttributePO::getCustomer,query.getCustomer())
                .eq(query.getBusinessModel() != null,AttributePO::getBusinessModel,query.getBusinessModel())
                .eq(query.getCoreInvest() != null,AttributePO::getCoreInvest,query.getCoreInvest())
                .eq(query.getProductType() != null,AttributePO::getProductType,query.getProductType())
                .eq(query.getCreator() != null,AttributePO::getCreator,query.getCreator())
                .list();
        return BeanUtil.copyToList(poList, AttributeVO.class);
    }

    /** 改 单个 Project Attribute*/
    @Operation(summary = "改 单个 Attribute")
    @PutMapping("/{prjCode}")
    public ResponseEntity<String> updateAttribute(@PathVariable("prjCode") String prjCode, @RequestBody AttributeDTO dto){

        dto.setPrjCode(dto.getPrjCode().trim().toUpperCase());
        dto.setPrjName(dto.getPrjName().trim());

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        attributeService.lambdaUpdate()
                .eq(AttributePO::getPrjCode,prjCode)
                .set(dto.getPrjCode() != null && !dto.getPrjCode().isEmpty() , AttributePO::getPrjCode,dto.getPrjCode())
                .set(dto.getPrjName() != null && !dto.getPrjName().isEmpty() , AttributePO::getPrjName,dto.getPrjName())
                .set(dto.getBu() != null && !dto.getBu().isEmpty(), AttributePO::getBu,dto.getBu())
                .set(dto.getCustomer() != null && !dto.getCustomer().isEmpty() , AttributePO::getCustomer,dto.getCustomer())
                .set(dto.getBusinessModel() != null && !dto.getBusinessModel().isEmpty() , AttributePO::getBusinessModel,dto.getBusinessModel())
                .set(dto.getCoreInvest() != null && !dto.getCoreInvest().isEmpty() , AttributePO::getCoreInvest,dto.getCoreInvest())
                .set(dto.getProductType() != null && !dto.getProductType().isEmpty() , AttributePO::getProductType,dto.getProductType())
                .set(AttributePO::getAttributeUpdateTime,LocalDateTime.now())
                .set(AttributePO::getAttributeUpdater,principals)
                .update();
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

