package com.ekz.ekzweb.controller.kpi;

import com.ekz.ekzweb.domain.kpi.Tss;
import com.ekz.ekzweb.service.kpi.ITssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "KPI TSS 接口")
@RestController
@RequestMapping("/kpi/tss")
public class TssController {
    @Autowired
    private ITssService service;

    @Operation(summary = "依id查")
    @GetMapping("/getOneById/{id}")
    public Tss getOneById(@PathVariable String id) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.getById(id);
    }

    @Operation(summary = "依月份查YYYY-MM")
    @GetMapping("/getByItemYearMonth/{itemYearMonth}")
    public List<Tss> getByItemYearMonth(@PathVariable String itemYearMonth) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.lambdaQuery().eq(Tss::getItemYearMonth,itemYearMonth).list();
    }

    @Operation(summary = "增 批量")
    @PostMapping("/saveBatch")
    public ResponseEntity<String> saveBatch(@RequestBody List<Tss> tssList){
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        service.saveBatch( tssList);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "依ids删除 批量")
    @DeleteMapping("/deleteByIds")
    public ResponseEntity<String> deleteByIds(@RequestBody List<String> ids) {
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        service.removeBatchByIds(ids);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "依月份删除 批量")
    @DeleteMapping("/deleteByItemYearMonth/{itemYearMonth}")
    public ResponseEntity<String> deleteByItemYearMonth(@PathVariable String itemYearMonth) {
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        service.lambdaUpdate().eq(Tss::getItemYearMonth,itemYearMonth).remove();
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


}
