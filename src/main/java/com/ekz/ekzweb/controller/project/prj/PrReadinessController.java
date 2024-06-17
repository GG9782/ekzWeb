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
    /** 01.11.1 查 单个 PrjReadiness */
    @Operation(summary = "01.10.1 查 单个 PrjReadiness")
    @GetMapping("/{prjCode}")
    public PrjReadinessVO prjReadinessById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( prjReadinessService.getById(prjCode) ,PrjReadinessVO.class);
    }

    /** 01.11.2 改 单个 PrjReadiness*/
    @Operation(summary = "01.10.2 改 单个 PrjReadiness")
    @PutMapping
    public ResponseEntity<String> updatePrjReadiness(@RequestBody PrjReadinessDTO dto){
        //  把DTO拷贝到PO
        PrjReadinessPO po = BeanUtil.copyProperties(dto,PrjReadinessPO.class);
        try {
            Subject subject = SecurityUtils.getSubject();
            po.setPrjReadinessUpdater(subject.getPrincipals().toString());
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }
        po.setPrjReadinessUpdateTime(LocalDateTime.now());
        // 新增
        prjReadinessService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}

