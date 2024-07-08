package com.ekz.ekzweb.controller.developer;

import com.ekz.ekzweb.domain.developer.po.DeveloperRecord;
import com.ekz.ekzweb.domain.developer.query.DeveloperRecordQuery;
import com.ekz.ekzweb.service.developer.IDeveloperRecordService;

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

@Tag(name = "developer 接口")
@RestController
@RequestMapping("/developer/record")
public class DeveloperRecordController {
    @Autowired
    private IDeveloperRecordService service;

    /** 全查 */
    @Operation(summary = "全查")
    @GetMapping("/all")
    public List<DeveloperRecord> getAll() {
        return service.list();
    }

    /** 依id查 */
    @Operation(summary = "依id查")
    @GetMapping("/getById/{id}")
    public DeveloperRecord getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    /** 多条件 查 */
    @Operation(summary = "多条件 查")
    @GetMapping("/query")
    public List<DeveloperRecord> query(DeveloperRecordQuery query ) {
        // checkPermission(prjCode+":member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("developer");

        return service.lambdaQuery()
                .eq(query.getLayer() != null,DeveloperRecord::getLayer ,query.getLayer())
                .eq(query.getSubLayer() != null,DeveloperRecord::getSubLayer,query.getSubLayer())
                .like(query.getDescription() != null,DeveloperRecord::getDescription ,query.getDescription())
                .between(query.getEarliestFinishDate() != null, DeveloperRecord::getFinishDate, query.getEarliestFinishDate(), query.getLatestFinishDate())
                .list();
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody DeveloperRecord po){
        // checkRole("developer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("developer");

        po.setId(null);
        po.setCreateTime(LocalDateTime.now());
        po.setCreator(subject.getPrincipals().toString());
        service.save(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody DeveloperRecord po){
        // checkRole("developer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("developer");

        String principals = subject.getPrincipals().toString();
        po.setCreator(principals);
        po.setCreateTime(LocalDateTime.now());
        service.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        // checkRole("developer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("developer");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
