package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.ScheduleDTO;
import com.ekz.ekzweb.domain.jsonType.EventAndDay;
import com.ekz.ekzweb.domain.jsonType.ScheduleJsonType;
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
import java.util.List;

@Tag(name = "Project schedule接口")
@RestController
@RequestMapping("/prj/schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

/** Project Schedule*/

    /** 查 单个 Project Schedule*/
    @Operation(summary = "查 单个 Schedule")
    @GetMapping("/{prjCode}")
    public ScheduleVO getScheduleById(@PathVariable("prjCode") String prjCode) {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return BeanUtil.copyProperties( scheduleService.getById(prjCode) ,ScheduleVO.class);
    }

    /** 改 单个 Project Schedule*/
    @Operation(summary = "改 单个 Schedule")
    @PutMapping
    public ResponseEntity<String> updateSchedule(@RequestBody ScheduleDTO dto){
        // checkPermission(prjCode+":member")
        String prjCode = dto.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        // 遍历 验证日期先后关系 并 补全每个阶段的endDate。
        String previousEndDate = null;
        LocalDate nextStartDate = null;
        for (int i = dto.getSchedule().size() - 1; i >= 0; i--) {
            ScheduleJsonType scheduleStage = dto.getSchedule().get(i);
            String startDate = scheduleStage.getStartDate();

            // 在这里验证Stage日期先后关系
            if( (scheduleStage.getEndDate() != null && LocalDate.parse(scheduleStage.getEndDate()).isBefore(LocalDate.parse(startDate)) ) ||
                    ( nextStartDate != null && startDate != null && ! LocalDate.parse(startDate).isBefore(nextStartDate) )
            ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( "Wrong date order at stage: "+ scheduleStage.getName() );
            }

            // 在这里补全每个阶段的endDate，如果startDate为null则跳过。
            if(startDate != null){
                // 在这里补全每个阶段的endDat。
                if(previousEndDate != null){
                    scheduleStage.setEndDate(previousEndDate);
                }
                previousEndDate = LocalDate.parse(startDate).minusDays(1).toString();
                nextStartDate = LocalDate.parse(startDate);
            }

            if(startDate != null) {
                // 验证事件日期合法性
                List<EventAndDay> events = scheduleStage.getEvents();
                boolean isStartEvent = false;
                for (EventAndDay event : events) {
                    LocalDate eventDate = LocalDate.parse(event.getEventDay());

                    if (eventDate.isBefore(LocalDate.parse(startDate)) || eventDate.isAfter(LocalDate.parse(scheduleStage.getEndDate()))) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event: " + event.getEventName() + " is out of the date range of stage:" + scheduleStage.getName() + " !");
                    }
                    if (eventDate.isEqual( LocalDate.parse(startDate)) ) {
                        isStartEvent = true;
                    }
                }
                if (isStartEvent == false) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("StdStage: " + scheduleStage.getName() + " is missing a start event !");
                }

                // 验证子事件日期合法性
                List<EventAndDay> subEvents = scheduleStage.getSubEvents();
                for (EventAndDay subEvent : subEvents) {
                    LocalDate subEventDate = LocalDate.parse(subEvent.getEventDay());
                    if (subEventDate.isBefore(LocalDate.parse(startDate)) || subEventDate.isAfter(LocalDate.parse(scheduleStage.getEndDate()))) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SubEvent: " + subEvent.getEventName() + " is out of the date range of stage:" + scheduleStage.getName() + " !");
                    }
                }
            }
        }

        //  把DTO拷贝到PO
        SchedulePO po = BeanUtil.copyProperties(dto,SchedulePO.class);
        po.setScheduleUpdater(subject.getPrincipals().toString());
        po.setScheduleUpdateTime(LocalDateTime.now());
        // 新增
        scheduleService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

