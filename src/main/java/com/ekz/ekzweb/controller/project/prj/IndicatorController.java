package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.IndicatorDTO;
import com.ekz.ekzweb.domain.project.prj.po.IndicatorPO;
import com.ekz.ekzweb.domain.project.prj.vo.IndicatorVO;
import com.ekz.ekzweb.service.project.prj.IIndicatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Project indicator接口")
@RestController
@RequestMapping("/prj/indicator")
public class IndicatorController {

    @Autowired
    private IIndicatorService indicatorService;



/** Project Indicator*/

    /** 查 单个 Project Indicator*/
    @Operation(summary = "查 单个 Indicator")
    @GetMapping("/{prjCode}")
    public IndicatorVO getIndicatorById(@PathVariable("prjCode") String prjCode) {
        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return BeanUtil.copyProperties( indicatorService.getById(prjCode) ,IndicatorVO.class);
    }

    /** 改 单个 Project Indicator*/
    @Operation(summary = "改 单个 Indicator")
    @PutMapping
    public ResponseEntity<String> updateIndicator( @RequestBody IndicatorDTO dto){
        //  把DTO拷贝到PO
        IndicatorPO po = BeanUtil.copyProperties(dto,IndicatorPO.class);
        Subject subject = SecurityUtils.getSubject();
        po.setIndicatorUpdater(subject.getPrincipals().toString());
        po.setIndicatorUpdateTime(LocalDateTime.now());
        // 新增
        indicatorService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

