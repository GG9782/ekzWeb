package com.ekz.ekzweb.controller.department;


import com.ekz.ekzweb.domain.department.Department;
import com.ekz.ekzweb.service.developer.department.IDepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Tag(name = "Department 接口")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    /** 全查 */
    @Operation(summary = "全查")
    @GetMapping("/all")
    public List<Department> getAll() {
        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");
        return service.list();
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Department po){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        service.save(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody Department po){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        service.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
