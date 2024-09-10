package com.ekz.ekzweb.controller.kpi;

import com.ekz.ekzweb.domain.kpi.TssAdditionalRules;
import com.ekz.ekzweb.service.kpi.ITssAdditionalRulesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Tag(name = "KPI TssAdditionalRules接口")
@RestController
@RequestMapping("/kpi/tssAdditionalRules")
public class TssAdditionalRulesController {

    @Autowired
    private ITssAdditionalRulesService service;

    @Operation(summary = "依id查")
    @GetMapping("/getOneById/{id}")
    public TssAdditionalRules getOneById(@PathVariable String id) {
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
    public ResponseEntity<String> saveOne(@RequestBody TssAdditionalRules rule){
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        service.save(rule);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody TssAdditionalRules rule){
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiEditor");

        service.updateById(rule);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
