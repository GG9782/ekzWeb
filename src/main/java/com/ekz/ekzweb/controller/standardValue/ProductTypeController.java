package com.ekz.ekzweb.controller.standardValue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ekz.ekzweb.domain.standardValue.StdProductType;
import com.ekz.ekzweb.service.standardValue.IProductTypeService;
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


@Tag(name = "StandardValue StdProductType 接口")
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private IProductTypeService service;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<String> getAllList() {
        return service.listObjs(new LambdaQueryWrapper<StdProductType>().select(StdProductType::getProductType));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdProductType> getAllObject() {
        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{productType}")
    public ResponseEntity<String> Delete(@PathVariable("productType") String productType){
        service.removeById(productType);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{productType}")
    public ResponseEntity<String> save(@PathVariable("productType") String productType){

        StdProductType dto = new StdProductType();
        productType = productType.trim().substring(0, 1).toUpperCase() + productType.trim().substring(1).toLowerCase();
        dto.setProductType(productType);

        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping("/{currentProductType}/{newProductType}")
    public ResponseEntity<String> update(@PathVariable("currentProductType") String currentProductType, @PathVariable("newProductType") String newProductType){


        StdProductType dto = new StdProductType();
        dto.setProductType(newProductType);

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.update(
                dto,new LambdaUpdateWrapper<StdProductType>().eq(StdProductType::getProductType,currentProductType)
        );
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
