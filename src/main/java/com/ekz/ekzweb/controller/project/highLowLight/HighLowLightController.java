package com.ekz.ekzweb.controller.project.highLowLight;

import com.ekz.ekzweb.domain.project.highLowLight.HighLowLight;
import com.ekz.ekzweb.service.project.highLowLight.IHighLowLightService;
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

@Tag(name = "Project HighLowLight 接口")
@RestController
@RequestMapping("/prj/highLowLight")
public class HighLowLightController {

    @Autowired
    private IHighLowLightService service;

/** Project HighLowLight */

    /** 查 单个*/
    @Operation(summary = "依 prjCode 查")
    @GetMapping("/{prjCode}")
    public List<HighLowLight> getByPrjCode(@PathVariable("prjCode") String prjCode) {
        return service.lambdaQuery()
                .eq(HighLowLight::getPrjCode,prjCode)
                .list();
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
    public ResponseEntity<String> save(@RequestBody HighLowLight highLowLight){
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
        highLowLight.setCreator(principals);
        highLowLight.setCreateTime(LocalDateTime.now());
        service.save(highLowLight);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody HighLowLight highLowLight){
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
        highLowLight.setCreator(principals);
        highLowLight.setCreateTime(LocalDateTime.now());
        service.updateById(highLowLight);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增  批量*/
    @Operation(summary = "增  批量")
    @PostMapping("/list")
    public ResponseEntity<String> save(@RequestBody List<HighLowLight> highLowLightList){
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
        for (HighLowLight highLowLight : highLowLightList) {
            highLowLight.setCreator(principals);
            highLowLight.setCreateTime(LocalDateTime.now());
        }
        service.saveBatch(highLowLightList);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

