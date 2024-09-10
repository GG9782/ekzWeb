package com.ekz.ekzweb.controller.kpi;

import com.ekz.ekzweb.domain.kpi.BusinessTrip;
import com.ekz.ekzweb.domain.kpi.MpIssue;
import com.ekz.ekzweb.service.kpi.IBusinessTripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Tag(name = "KPI BusinessTrip接口")
@RestController
@RequestMapping("/kpi/businessTrip")
public class BusinessTripController {
    @Autowired
    private IBusinessTripService service;

    @Operation(summary = "getSumGroupByMonthAndCustomer")
    @GetMapping("/getSumGroupByMonthAndCustomer/{itemYear}")
    public List<Map<String,Object>> getSumGroupByMonthAndCustomer(@PathVariable Integer itemYear) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.getSumGroupByMonthAndCustomer(itemYear);
    }

    @Operation(summary = "全查")
    @GetMapping("/getAll")
    public List<BusinessTrip> getOneAll() {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.lambdaQuery().orderByAsc(BusinessTrip::getStartDate).list();
    }

    @Operation(summary = "依id查")
    @GetMapping("/getOneById/{id}")
    public BusinessTrip getOneById(@PathVariable String id) {
        // checkRole("kpiViewer")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.getById(id);
    }

    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteById(@PathVariable("id") String id){

        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("sectionChief");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> saveOne(@RequestBody BusinessTrip po){
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("sectionChief");

        if(po.getStartDate().isAfter(po.getEndDate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start Date is after End Date!");
        }

        String principals = subject.getPrincipals().toString();
        po.setCreator(principals);
        po.setCreateTime(LocalDateTime.now());

        po.setItemYear(po.getStartDate().getYear());
        po.setItemMonth(po.getStartDate().getMonth());

        po.setDays( 1 + (int) ChronoUnit.DAYS.between( po.getStartDate(), po.getEndDate() ) );

        service.save(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody BusinessTrip po){
        // checkRole("kpiEditor")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("sectionChief");

        if(po.getStartDate().isAfter(po.getEndDate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start Date is after End Date!");
        }

        String principals = subject.getPrincipals().toString();
        po.setCreator(principals);
        po.setCreateTime(LocalDateTime.now());

        if(po.getStartDate() != null ){
            po.setItemYear(po.getStartDate().getYear());
            po.setItemMonth(po.getStartDate().getMonth());
        }
        po.setDays( 1 + (int) ChronoUnit.DAYS.between( po.getStartDate(), po.getEndDate() ) );

        service.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
