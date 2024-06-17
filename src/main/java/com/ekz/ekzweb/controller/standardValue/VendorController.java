package com.ekz.ekzweb.controller.standardValue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ekz.ekzweb.domain.standardValue.Vendor;
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

@Tag(name = "StandardValue Vendor 接口")
@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private IVendorService service;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<String> getAllList() {
        return service.listObjs(new LambdaQueryWrapper<Vendor>().select(Vendor::getVendor));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<Vendor> getAllObject() {
        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{vendor}")
    public ResponseEntity<String> Delete(@PathVariable("vendor") String vendor){
        service.removeById(vendor);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{vendor}")
    public ResponseEntity<String> save(@PathVariable("vendor") String vendor){
        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }
        Vendor dto = new Vendor();
        dto.setVendor(vendor.trim());

        Subject subject = SecurityUtils.getSubject();
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
        service.removeByIds(vendors);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 多个 */
    @Operation(summary = "增 多个")
    @PostMapping("/vendors")
    public ResponseEntity<String> save(@RequestBody List<String> vendors) {

        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Subject subject = SecurityUtils.getSubject();
        List<Vendor> dtos = new ArrayList<>();
        String principals = subject.getPrincipals().toString();
        LocalDateTime createTime = LocalDateTime.now();
        for (String vendor : vendors) {
            Vendor dto = new Vendor();
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

        try {
            Subject subject = SecurityUtils.getSubject();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Subject subject = SecurityUtils.getSubject();
        Vendor dto = new Vendor();
        dto.setVendor(newVendor);
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.update(
                dto,new LambdaUpdateWrapper<Vendor>().eq(Vendor::getVendor,currentVendor)
        );
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
