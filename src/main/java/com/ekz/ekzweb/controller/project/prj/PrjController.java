package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.AttributeDTO;
import com.ekz.ekzweb.domain.project.prj.po.Project;
import com.ekz.ekzweb.domain.project.prj.query.AttributeQuery;
import com.ekz.ekzweb.domain.project.prj.vo.AttributeVO;
import com.ekz.ekzweb.domain.project.prj.vo.OverviewVO;
import com.ekz.ekzweb.service.project.prj.IPrjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Project 基础接口")
@RestController
@RequestMapping("/prj")
public class PrjController {

    @Autowired
    private IPrjService prjService;


    /** 01.01.1 全查 Overview*/
    @Operation(summary = "01.01.1 全查 Overview")
    @GetMapping("/overview")
    public List<OverviewVO> queryOverview() {

        List<Project> poList = prjService.queryOverview();
        List<OverviewVO> voList = new ArrayList<>();

        for (Project project : poList) {
            OverviewVO overviewVO = BeanUtil.copyProperties(project,OverviewVO.class);
            overviewVO.setCurrentStage(project.getSchedule());
            voList.add(overviewVO);
        }
        return voList;
    }
    /** 01.02.1 增 单个 Project */
    @Operation(summary = "01.02.1 增 单个 Project")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody AttributeDTO dto){
        dto.setPrjCode( dto.getPrjCode().trim().toUpperCase() );
        dto.setPrjName( dto.getPrjName().trim() );
        Project po = BeanUtil.copyProperties(dto,Project.class);
        try {
            Subject subject = SecurityUtils.getSubject();
            po.setCreator(subject.getPrincipals().toString());
            po.setAttributeUpdater(po.getAttributeUpdater());
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }
        po.setCreateTime(LocalDateTime.now());
        po.setAttributeUpdateTime(po.getCreateTime());
        prjService.save(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

/** Project Attribute*/

    /** 01.03.2 复杂条件 查 多个 Attribute*/
    @Operation(summary = "01.03.2 复杂条件 查 多个 Attribute")
    @GetMapping("/attribute/list")
    public List<AttributeVO> queryPrjAttributeList(AttributeQuery query) {
        System.out.println(query);
        List<Project> attributePO = prjService.queryPrjAttributeList(
                query.getPrjCode(),
                query.getPrjName(),
                query.getBu(),
                query.getCustomer(),
                query.getBusinessModel(),
                query.getCoreInvest(),
                query.getCoreInvest(),
                query.getProductType(),
                query.getCreator(),
                query.getEarliestCreateDate(),
                query.getLatestCreateDate()
        );
        // 2.把PO拷贝到VO
        return BeanUtil.copyToList(attributePO, AttributeVO.class);
    }

    /** 01.03.3 改 单个 Project Attribute*/
    @Operation(summary = "01.03.3 改 单个 Attribute")
    @PutMapping("/attribute/{prjCode}")
    public ResponseEntity<String> updateAttribute(@PathVariable("prjCode") String prjCode, @RequestBody AttributeDTO dto){
        try {
            Subject subject = SecurityUtils.getSubject();
            String userId = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }
        dto.setPrjCode(dto.getPrjCode().trim().toUpperCase());
        dto.setPrjName(dto.getPrjName().trim());
        prjService.updateAttribute(prjCode, dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 01.03.4 逻辑删除 单个 Project Attribute*/
    @Operation(summary = "01.03.4 逻辑删除 单个 Attribute")
    @DeleteMapping("/attribute/logicDelete/{prjCode}")
    public ResponseEntity<String>  logicDeleteById(@PathVariable("prjCode") String prjCode){
        prjService.removeById(prjCode);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 01.03.5 逻辑删除撤销 单个 Project Attribute*/
    @Operation(summary = " 01.03.5 逻辑删除撤销 单个 Attribute")
    @DeleteMapping("/attribute/cancelLogicDelete/{prjCode}")
    public ResponseEntity<String>  cancelLogicDeleteById(@PathVariable("prjCode") String prjCode){
        prjService.cancelLogicDeleteById(prjCode);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 01.03.6 物理删除 单个 Project Attribute*/
    @Operation(summary = "01.03.6 物理删除 单个 Attribute")
    @DeleteMapping("/attribute/physicsDelete/{prjCode}")
    public ResponseEntity<String> physicsDeleteById(@PathVariable("prjCode") String prjCode){
        prjService.physicsDeleteById(prjCode);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

