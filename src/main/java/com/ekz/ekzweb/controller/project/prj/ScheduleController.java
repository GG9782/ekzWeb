package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.ScheduleDTO;
import com.ekz.ekzweb.domain.project.prj.jsonType.ScheduleJsonType;
import com.ekz.ekzweb.domain.project.prj.po.SchedulePO;
import com.ekz.ekzweb.domain.project.prj.vo.ScheduleVO;
import com.ekz.ekzweb.service.project.prj.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Tag(name = "Project schedule接口")
@RestController
@RequestMapping("/prj/schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

/** Project Schedule*/

    /** 01.06.1 查 单个 Project Schedule*/
    @Operation(summary = "01.06.1 查 单个 Schedule")
    @GetMapping("/{prjCode}")
    public ScheduleVO getScheduleById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( scheduleService.getById(prjCode) ,ScheduleVO.class);
    }

    /** 01.06.2改 单个 Project Schedule*/
    @Operation(summary = "01.06.2 改 单个 Schedule")
    @PutMapping
    public ResponseEntity<String> updateSchedule(@RequestBody ScheduleDTO dto){

        // 遍历 验证日期先后关系 并 补全每个阶段的endDate。
        String previousEndDate = null;
        LocalDate nextStartDate = null;
        for (int i = dto.getSchedule().size() - 1; i >= 0; i--) {
            ScheduleJsonType scheduleStage = dto.getSchedule().get(i);
            String startDate = scheduleStage.getStartDate();

            // 在这里验证日期先后关系
            if( (scheduleStage.getEndDate() != null && LocalDate.parse(scheduleStage.getEndDate()).isBefore(LocalDate.parse(startDate)) ) ||
                    ( nextStartDate != null && startDate != null && ! LocalDate.parse(startDate).isBefore(nextStartDate) )
            ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(scheduleStage.getName() + " 阶段日期先后关系错误");
            }

            // 在这里补全每个阶段的endDat，如果startDate为null则跳过。
            if(startDate != null){
                if(previousEndDate != null){
                    scheduleStage.setEndDate(previousEndDate);
                }
                previousEndDate = LocalDate.parse(startDate).minusDays(1).toString();
                nextStartDate = LocalDate.parse(startDate);
            }
        }

        //  把DTO拷贝到PO
        SchedulePO po = BeanUtil.copyProperties(dto,SchedulePO.class);
        Subject subject = SecurityUtils.getSubject();
        po.setScheduleUpdater(subject.getPrincipals().toString());
        po.setScheduleUpdateTime(LocalDateTime.now());
        // 新增
        scheduleService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

