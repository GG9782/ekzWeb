package com.ekz.ekzweb.controller.standardValue;



import com.ekz.ekzweb.domain.project.prj.po.AttributePO;

import com.ekz.ekzweb.domain.standardValue.Readiness;
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


@Tag(name = "StandardValue Readiness 接口")
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
    public List<Readiness> getByProject(@PathVariable("prjCode") String prjCode) {
        AttributePO attributePO = attributeService.getById(prjCode);
        return service.getByProject(prjCode,attributePO);
//        return service.lambdaQuery()
//                .eq(attributePO.getBu() != null,Readiness::getBu,attributePO.getBu())
//                .eq(attributePO.getCustomer() != null,Readiness::getCustomer,attributePO.getCustomer())
//                .eq(attributePO.getProductType() != null,Readiness::getProductType,attributePO.getProductType())
//                .orderByAsc(Readiness::getItem)
//                .list();
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<Readiness> getAllObject() {
        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable("id") String id){
        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Readiness readiness){
        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        readiness.setCreator(principals);
        readiness.setCreateTime(LocalDateTime.now());
        service.save(readiness);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody Readiness readiness){

        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        readiness.setCreator(principals);
        readiness.setCreateTime(LocalDateTime.now());
        service.updateById(readiness);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增  批量*/
    @Operation(summary = "增  批量")
    @PostMapping("/list")
    public ResponseEntity<String> save(@RequestBody List<Readiness> readinessList){
        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        for (Readiness readiness : readinessList) {
            readiness.setCreator(principals);
            readiness.setCreateTime(LocalDateTime.now());
        }
        service.saveBatch(readinessList);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
