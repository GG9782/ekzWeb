package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.jsonType.StringAndStyleJsonType;
import com.ekz.ekzweb.domain.project.prj.dto.AttributeDTO;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.project.prj.po.Project;
import com.ekz.ekzweb.domain.project.prj.query.AttributeQuery;
import com.ekz.ekzweb.domain.project.prj.vo.AttributeVO;
import com.ekz.ekzweb.domain.project.prj.vo.OverviewVO;
import com.ekz.ekzweb.service.perms.IUserProjectPermissionService;
import com.ekz.ekzweb.service.project.prj.IPrjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Project 基础接口")
@RestController
@RequestMapping("/prj")
public class PrjController {

    @Autowired
    private IPrjService prjService;

    @Autowired
    private IUserProjectPermissionService userProjectPermissionService;

    /** 全查 Overview*/
    @Operation(summary = "全查 Overview")
    @GetMapping("/overview")
    public List<OverviewVO> queryOverview() {

        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        List<Project> poList = prjService.queryOverview();
        List<OverviewVO> voList = new ArrayList<>();

        for (Project project : poList) {
            OverviewVO overviewVO = BeanUtil.copyProperties(project,OverviewVO.class);
            overviewVO.setCurrentStage(project.getSchedule());
            // get4Indicator
            if(project.getIndicatorUserDefine() != null){
                for ( StringAndStyleJsonType jsonOne: project.getIndicatorUserDefine()){
                    switch (jsonOne.getContent()) {
                        case "Cost" -> overviewVO.setIndicatorCost(jsonOne.getStyle());
                        case "Quality" -> overviewVO.setIndicatorQuality(jsonOne.getStyle());
                        case "Resource" -> overviewVO.setIndicatorResource(jsonOne.getStyle());
                        case "Schedule" -> overviewVO.setIndicatorSchedule(jsonOne.getStyle());
                    }
                }
            }
            voList.add(overviewVO);
        }
        return voList;
    }

    /** 增 单个 Project */
    @Operation(summary = "增 单个 Project")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody AttributeDTO dto){

        // checkRole("projectManager")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("manager");

        // save project
        dto.setPrjCode( dto.getPrjCode().trim().toUpperCase() );
        dto.setPrjName( dto.getPrjName().trim() );
        Project po = BeanUtil.copyProperties(dto,Project.class);

        po.setCreator(subject.getPrincipals().toString());
        po.setAttributeUpdater(po.getAttributeUpdater());
        po.setCreateTime(LocalDateTime.now());
        po.setAttributeUpdateTime(po.getCreateTime());
        prjService.save(po);

        // save userProjectPermission manager
        userProjectPermissionService.saveOne(po.getCreator(), po.getPrjCode(), "manager");
        userProjectPermissionService.saveOne(po.getCreator(), po.getPrjCode(), "member");

        // return
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 逻辑删除 单个 Project*/
    @Operation(summary = "逻辑删除 单个 Project")
    @DeleteMapping("/logicDelete/{prjCode}")
    public ResponseEntity<String>  logicDeleteById(@PathVariable("prjCode") String prjCode){

        // checkPermission(prjCode+":manager")
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":manager");

        // 改prjCode、prjName防止后续重名报错
        String deleteRemark = "@"+ LocalDateTime.now().toString();
        Project project = prjService.getById(prjCode);
        String prjName= project.getPrjName();
        prjService.lambdaUpdate()
                .eq(Project::getPrjCode,prjCode)
                .set(Project::getPrjCode,prjCode + deleteRemark)
                .set(Project::getPrjName,prjName + deleteRemark)
                .update();
        // logicDelete
        prjService.removeById(prjCode  + deleteRemark);

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 逻辑删除撤销 单个 Project*/
    @Operation(summary = "逻辑删除撤销 单个 Projecte")
    @DeleteMapping("/undoLogicDelete/{prjCode}")
    public ResponseEntity<String>  undoLogicDeleteById(@PathVariable("prjCode") String prjCode){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        // undoLogicDelete
        prjService.cancelLogicDeleteById(prjCode);

        // 还原prjCode、prjName
        String originalPrjCode = prjCode.substring(0, prjCode.indexOf("@"));
        prjService.lambdaUpdate()
                .eq(Project::getPrjCode,prjCode)
                .set(Project::getPrjCode,originalPrjCode)
                .set(Project::getPrjName,originalPrjCode)
                .update();

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 物理删除 单个 Project*/
    @Operation(summary = "物理删除 单个 Project")
    @DeleteMapping("/physicsDelete/{prjCode}")
    public ResponseEntity<String> physicsDeleteById(@PathVariable("prjCode") String prjCode){

        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        prjService.physicsDeleteById(prjCode);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 查 逻辑删除的Project*/
    @Operation(summary = "查 逻辑删除的Project")
    @GetMapping("/getLogicDelete/")
    public List<AttributePO> getLogicDelete(){

        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        return prjService.getLogicDeleteProject();
    }


}

