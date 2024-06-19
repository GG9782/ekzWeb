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


    /** 全查 Overview*/
    @Operation(summary = "全查 Overview")
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
    /** 增 单个 Project */
    @Operation(summary = "增 单个 Project")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody AttributeDTO dto){
        dto.setPrjCode( dto.getPrjCode().trim().toUpperCase() );
        dto.setPrjName( dto.getPrjName().trim() );
        Project po = BeanUtil.copyProperties(dto,Project.class);

        Subject subject = SecurityUtils.getSubject();
        po.setCreator(subject.getPrincipals().toString());
        po.setAttributeUpdater(po.getAttributeUpdater());
        po.setCreateTime(LocalDateTime.now());
        po.setAttributeUpdateTime(po.getCreateTime());
        prjService.save(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 逻辑删除 单个 Project*/
    @Operation(summary = "逻辑删除 单个 Project")
    @DeleteMapping("/attribute/logicDelete/{prjCode}")
    public ResponseEntity<String>  logicDeleteById(@PathVariable("prjCode") String prjCode){
        prjService.removeById(prjCode);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 01.03.5 逻辑删除撤销 单个 Project*/
    @Operation(summary = "逻辑删除撤销 单个 Projecte")
    @DeleteMapping("/attribute/cancelLogicDelete/{prjCode}")
    public ResponseEntity<String>  cancelLogicDeleteById(@PathVariable("prjCode") String prjCode){
        prjService.cancelLogicDeleteById(prjCode);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 01.03.6 物理删除 单个 Project*/
    @Operation(summary = "物理删除 单个 Project")
    @DeleteMapping("/attribute/physicsDelete/{prjCode}")
    public ResponseEntity<String> physicsDeleteById(@PathVariable("prjCode") String prjCode){
        prjService.physicsDeleteById(prjCode);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

