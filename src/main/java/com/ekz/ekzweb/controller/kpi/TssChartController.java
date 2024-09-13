package com.ekz.ekzweb.controller.kpi;

import com.ekz.ekzweb.service.kpi.ITssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "KPI TSS Chart接口")
@RestController
@RequestMapping("/kpi/tssChart")
public class TssChartController {
    @Autowired
    private ITssService service;

    @Operation(summary = "getByGroupingRule1")
    @GetMapping("/getByGroupingRule1/{itemYearMonth}")
    public List<Map<String, Object>> getByGroupingRule1(@PathVariable String itemYearMonth) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.getByGroupingRule1(itemYearMonth);
    }

    @Operation(summary = "getOTByDepartmentAndName")
    @GetMapping("/getOTByDepartmentAndName/{itemYearMonth}")
    public List<Map<String, Object>> getOTByDepartmentAndName(@PathVariable String itemYearMonth) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.getOTByDepartmentAndName(itemYearMonth);
    }

    @Operation(summary = "getByDepartmentAndCustomer")
    @GetMapping("/getByDepartmentAndCustomer/{itemYearMonth}")
    public List<Map<String, Object>> getByDepartmentAndCustomer(@PathVariable String itemYearMonth) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.getByDepartmentAndCustomer(itemYearMonth);
    }

    @Operation(summary = "getByDepartmentProjectAdditionalRules")
    @GetMapping("/getByDepartmentProjectAdditionalRules/{itemYearMonth}")
    public List<Map<String, Object>> getByDepartmentProjectAdditionalRules(@PathVariable String itemYearMonth) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.getByDepartmentProjectAdditionalRules(itemYearMonth);
    }

    @Operation(summary = "getByDepartment")
    @GetMapping("/getByDepartment/{itemYearMonth}")
    public List<Map<String, Object>> getByDepartment(@PathVariable String itemYearMonth) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("kpiViewer");

        return service.getByDepartment(itemYearMonth);
    }

}
