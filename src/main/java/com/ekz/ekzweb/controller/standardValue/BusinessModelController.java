package com.ekz.ekzweb.controller.standardValue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ekz.ekzweb.domain.standardValue.StdBusinessModel;
import com.ekz.ekzweb.service.standardValue.IBusinessModelService;
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


@Tag(name = "StandardValue BusinessModel 接口")
@RestController
@RequestMapping("/businessModel")
public class BusinessModelController {

    @Autowired
    private IBusinessModelService service;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<String> getAllList() {
        return service.listObjs(new LambdaQueryWrapper<StdBusinessModel>().select(StdBusinessModel::getBusinessModel));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdBusinessModel> getAllObject() {
        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{businessModel}")
    public ResponseEntity<String> Delete(@PathVariable("businessModel") String businessModel){
        service.removeById(businessModel);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{businessModel}")
    public ResponseEntity<String> save(@PathVariable("businessModel") String businessModel){

        StdBusinessModel dto = new StdBusinessModel();
        dto.setBusinessModel(businessModel.trim().toUpperCase());
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);

        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping("/{currentBusinessModel}/{newBusinessModel}")
    public ResponseEntity<String> update(@PathVariable("currentBusinessModel") String currentBusinessModel, @PathVariable("newBusinessModel") String newBusinessModel){

        StdBusinessModel dto = new StdBusinessModel();
        dto.setBusinessModel(newBusinessModel.trim().toUpperCase());

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);

        dto.setCreateTime(LocalDateTime.now());
        service.update(
                dto,new LambdaUpdateWrapper<StdBusinessModel>().eq(StdBusinessModel::getBusinessModel,currentBusinessModel)
        );
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
