package com.ekz.ekzweb.controller.standardValue;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ekz.ekzweb.domain.standardValue.StdBu;
import com.ekz.ekzweb.service.standardValue.IBuService;
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


@Tag(name = "StandardValue Bu 接口")
@RestController
@RequestMapping("/bu")
public class BuController {

    @Autowired
    private IBuService service;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<String> getAllList() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.listObjs(new LambdaQueryWrapper<StdBu>().select(StdBu::getBu));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdBu> getAllObject() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{bu}")
    public ResponseEntity<String> Delete(@PathVariable("bu") String bu){
        // checkPermission(projectManager")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("departmentHead");

        service.removeById(bu);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{bu}")
    public ResponseEntity<String> save(@PathVariable("bu") String bu){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("departmentHead");

        StdBu dto = new StdBu();
        dto.setBu(bu.trim().toUpperCase());
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);

        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping("/{currentBu}/{newBu}")
    public ResponseEntity<String> update(@PathVariable("currentBu") String currentBu, @PathVariable("newBu") String newBu){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("departmentHead");

        StdBu dto = new StdBu();
        dto.setBu(newBu.trim().toUpperCase());
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.update(
                dto,new LambdaUpdateWrapper<StdBu>().eq(StdBu::getBu,currentBu)
        );
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
