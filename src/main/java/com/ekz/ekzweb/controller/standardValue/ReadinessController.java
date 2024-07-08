package com.ekz.ekzweb.controller.standardValue;



import com.ekz.ekzweb.domain.project.prj.po.AttributePO;

import com.ekz.ekzweb.domain.standardValue.StdReadiness;
import com.ekz.ekzweb.service.project.prj.IAttributeService;
import com.ekz.ekzweb.service.standardValue.IReadinessService;
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


@Tag(name = "StandardValue StdReadiness 接口")
@RestController
@RequestMapping("/readiness")
public class ReadinessController {

    @Autowired
    private IReadinessService service;
    @Autowired
    private IAttributeService attributeService;

    /** 依project查 Object*/
    @Operation(summary = "依project查 Object")
    @GetMapping("/getByPrjCode/{prjCode}")
    public List<StdReadiness> getByProject(@PathVariable("prjCode") String prjCode) {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        AttributePO attributePO = attributeService.getById(prjCode);
        return service.getByProject(prjCode,attributePO);
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdReadiness> getAllObject() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable("id") String id){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody StdReadiness stdReadiness){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        String principals = subject.getPrincipals().toString();
        stdReadiness.setCreator(principals);
        stdReadiness.setCreateTime(LocalDateTime.now());
        service.save(stdReadiness);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody StdReadiness stdReadiness){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        String principals = subject.getPrincipals().toString();
        stdReadiness.setCreator(principals);
        stdReadiness.setCreateTime(LocalDateTime.now());
        service.updateById(stdReadiness);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增  批量*/
    @Operation(summary = "增  批量")
    @PostMapping("/list")
    public ResponseEntity<String> save(@RequestBody List<StdReadiness> stdReadinessList){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        String principals = subject.getPrincipals().toString();
        for (StdReadiness stdReadiness : stdReadinessList) {
            stdReadiness.setCreator(principals);
            stdReadiness.setCreateTime(LocalDateTime.now());
        }
        service.saveBatch(stdReadinessList);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
