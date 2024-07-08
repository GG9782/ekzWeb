package com.ekz.ekzweb.controller.standardValue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ekz.ekzweb.domain.standardValue.StdVendor;
import com.ekz.ekzweb.service.standardValue.IVendorService;
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

@Tag(name = "StandardValue StdVendor 接口")
@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private IVendorService service;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<String> getAllList() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.listObjs(new LambdaQueryWrapper<StdVendor>().select(StdVendor::getVendor));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdVendor> getAllObject() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{vendor}")
    public ResponseEntity<String> Delete(@PathVariable("vendor") String vendor){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        service.removeById(vendor);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{vendor}")
    public ResponseEntity<String> save(@PathVariable("vendor") String vendor){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");


        StdVendor dto = new StdVendor();
        dto.setVendor(vendor.trim());

        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);

        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 删 多个*/
    @Operation(summary = "删 多个")
    @DeleteMapping("/vendors")
    public ResponseEntity<String> delete(@RequestBody List<String> vendors) {
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        service.removeByIds(vendors);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 多个 */
    @Operation(summary = "增 多个")
    @PostMapping("/vendors")
    public ResponseEntity<String> save(@RequestBody List<String> vendors) {
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        List<StdVendor> dtos = new ArrayList<>();
        String principals = subject.getPrincipals().toString();
        LocalDateTime createTime = LocalDateTime.now();
        for (String vendor : vendors) {
            StdVendor dto = new StdVendor();
            dto.setVendor(vendor);
            dto.setCreator(principals);
            dto.setCreateTime(createTime);
            dtos.add(dto);
        }

        service.saveBatch(dtos);

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping("/{currentVendor}/{newVendor}")
    public ResponseEntity<String> update(@PathVariable("currentVendor") String currentVendor, @PathVariable("newVendor") String newVendor){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        StdVendor dto = new StdVendor();
        dto.setVendor(newVendor);
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.update(
                dto,new LambdaUpdateWrapper<StdVendor>().eq(StdVendor::getVendor,currentVendor)
        );
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
