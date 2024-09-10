package com.ekz.ekzweb.controller.kpi;

import com.ekz.ekzweb.domain.kpi.MpIssue;
import com.ekz.ekzweb.service.kpi.IMpIssueService;
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

@Tag(name = "KPI MpIssue接口")
@RestController
@RequestMapping("/kpi/mpIssue")
public class MpIssueController {
    @Autowired
    private IMpIssueService service;

    @Operation(summary = "全查")
    @GetMapping("/getAll")
    public List<MpIssue> getAll() {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.lambdaQuery().orderByAsc(MpIssue::getItemYear,MpIssue::getItemMonth).list();
    }

    @Operation(summary = "依年查")
    @GetMapping("/getByYearMonth/{itemYear}")
    public List<MpIssue> getByYearMonth(@PathVariable String itemYear) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.lambdaQuery()
                .eq(MpIssue::getItemYear,itemYear)
                .orderByAsc(MpIssue::getItemYear,MpIssue::getItemMonth)
                .list();
    }

    @Operation(summary = "依id查")
    @GetMapping("/getOneById/{id}")
    public MpIssue getOneById(@PathVariable String id) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.getById(id);
    }

    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteById(@PathVariable("id") String id){

        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> saveOne(@RequestBody MpIssue po){
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        String principals = subject.getPrincipals().toString();
        po.setCreator(principals);
        po.setCreateTime(LocalDateTime.now());

        po.setItemYear(po.getItemDate().getYear());
        po.setItemMonth(po.getItemDate().getMonth());

        service.save(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody MpIssue po){
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        String principals = subject.getPrincipals().toString();
        po.setCreator(principals);
        po.setCreateTime(LocalDateTime.now());

        if(po.getItemDate() != null){
            po.setItemYear(po.getItemDate().getYear());
            po.setItemMonth(po.getItemDate().getMonth());
        }

        service.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
