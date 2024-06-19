package com.ekz.ekzweb.controller.standardValue;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ekz.ekzweb.domain.standardValue.Bu;
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
        return service.listObjs(new LambdaQueryWrapper<Bu>().select(Bu::getBu));
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<Bu> getAllObject() {
        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{bu}")
    public ResponseEntity<String> Delete(@PathVariable("bu") String bu){
        service.removeById(bu);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{bu}")
    public ResponseEntity<String> save(@PathVariable("bu") String bu){
        Bu dto = new Bu();
        dto.setBu(bu.trim().toUpperCase());
        Subject subject = SecurityUtils.getSubject();
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

        Bu dto = new Bu();
        dto.setBu(newBu.trim().toUpperCase());
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);
        dto.setCreateTime(LocalDateTime.now());
        service.update(
                dto,new LambdaUpdateWrapper<Bu>().eq(Bu::getBu,currentBu)
        );
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
