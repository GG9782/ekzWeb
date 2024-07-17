package com.ekz.ekzweb.controller.standardValue;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.standardValue.StdIndicator;
import com.ekz.ekzweb.service.project.prj.IAttributeService;
import com.ekz.ekzweb.service.standardValue.IStdIndicatorService;
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


@Tag(name = "StandardValue Indicator 接口")
@RestController
@RequestMapping("/StdIndicator")
public class StdIndicatorController {

    @Autowired
    private IStdIndicatorService service;
    @Autowired
    private IAttributeService attributeService;

    /** 全查 List*/
    @Operation(summary = "依Customer 全查 List")
    @GetMapping("/getByCustomer/{customer}")
    public List<String> getByCustomer(@PathVariable String customer) {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.listObjs(new LambdaQueryWrapper<StdIndicator>().select(StdIndicator::getName).eq(StdIndicator::getCustomer,customer));
    }

    @Operation(summary = "依prjCode 全查 List")
    @GetMapping("/getByPrjCode/{prjCode}")
    public List<String> getByPrjCode(@PathVariable String prjCode) {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");
        AttributePO attributePO = attributeService.lambdaQuery().select(AttributePO::getCustomer).eq(AttributePO::getPrjCode,prjCode).one();
        String customer = attributePO.getCustomer();
        return service.listObjs(new LambdaQueryWrapper<StdIndicator>().select(StdIndicator::getName).eq(StdIndicator::getCustomer,customer));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdIndicator> getAllObject() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable Integer id){
        // checkPermission(projectManager")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody StdIndicator dto){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        dto.setName(dto.getName().trim());
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> update(@RequestBody StdIndicator dto){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        dto.setName(dto.getName().trim());
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.updateById(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
