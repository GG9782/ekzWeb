package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.PrjReadinessDTO;
import com.ekz.ekzweb.domain.project.prj.po.PrjReadinessPO;
import com.ekz.ekzweb.domain.project.prj.vo.PrjReadinessVO;
import com.ekz.ekzweb.service.project.prj.IPrjReadinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Project prjReadiness 接口")
@RestController
@RequestMapping("/prj/prjReadiness")
public class PrReadinessController {

    @Autowired
    private IPrjReadinessService prjReadinessService;

/** Project PrjReadiness */
    /** 查 单个 PrjReadiness */
    @Operation(summary = "查 单个 PrjReadiness")
    @GetMapping("/{prjCode}")
    public PrjReadinessVO prjReadinessById(@PathVariable("prjCode") String prjCode) {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return BeanUtil.copyProperties( prjReadinessService.getById(prjCode) ,PrjReadinessVO.class);
    }

    /** 改 单个 PrjReadiness*/
    @Operation(summary = "改 单个 PrjReadiness")
    @PutMapping
    public ResponseEntity<String> updatePrjReadiness(@RequestBody PrjReadinessDTO dto){
        // checkPermission(prjCode+":member")
        String prjCode = dto.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        //  把DTO拷贝到PO
        PrjReadinessPO po = BeanUtil.copyProperties(dto,PrjReadinessPO.class);
        po.setPrjReadinessUpdater(subject.getPrincipals().toString());
        po.setPrjReadinessUpdateTime(LocalDateTime.now());
        // 新增
        prjReadinessService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}

