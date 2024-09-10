package com.ekz.ekzweb.controller.standardValue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ekz.ekzweb.domain.standardValue.StdCustomer;
import com.ekz.ekzweb.service.standardValue.ICustomerService;
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


@Tag(name = "StandardValue StdCustomer 接口")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<String> getAllList() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.listObjs(new LambdaQueryWrapper<StdCustomer>().select(StdCustomer::getCustomer));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdCustomer> getAllObject() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{customer}")
    public ResponseEntity<String> Delete(@PathVariable("customer") String customer){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("departmentHead");
        service.removeById(customer);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{customer}/{bu}")
    public ResponseEntity<String> save(@PathVariable("customer") String customer,@PathVariable("bu") String bu){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("departmentHead");

        StdCustomer dto = new StdCustomer();
        customer = customer.trim().substring(0, 1).toUpperCase() + customer.trim().substring(1).toLowerCase();
        dto.setCustomer(customer);
        dto.setBu(bu);
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping("/{currentCustomer}/{newCustomer}")
    public ResponseEntity<String> update(@PathVariable("currentCustomer") String currentCustomer, @PathVariable("newCustomer") String newCustomer) {
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("departmentHead");

        StdCustomer dto = new StdCustomer();
        dto.setCustomer(newCustomer.trim().toUpperCase());
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.update(
                dto,new LambdaUpdateWrapper<StdCustomer>().eq(com.ekz.ekzweb.domain.standardValue.StdCustomer::getCustomer,currentCustomer)
        );
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 删 多个*/
    @Operation(summary = "删 多个")
    @DeleteMapping("/customers")
    public ResponseEntity<String> delete(@RequestBody List<String> customers) {
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("departmentHead");

        service.removeByIds(customers);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 多个 */
    @Operation(summary = "增 多个")
    @PostMapping("/customers")
    public ResponseEntity<String> save(@RequestBody List<String> customers) {
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        List<StdCustomer> dtos = new ArrayList<>();
        String principals = subject.getPrincipals().toString();
        LocalDateTime createTime = LocalDateTime.now();

        for (String customer : customers) {
            StdCustomer dto = new StdCustomer();
            dto.setCustomer(customer.trim().toUpperCase());
            dto.setCreator(principals);
            dto.setCreateTime(createTime);
            dtos.add(dto);
        }

        service.saveBatch(dtos);

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
