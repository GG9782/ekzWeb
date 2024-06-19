package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.ApprovalDTO;
import com.ekz.ekzweb.domain.project.prj.po.ApprovalPO;
import com.ekz.ekzweb.domain.project.prj.vo.ApprovalVO;
import com.ekz.ekzweb.service.project.prj.IApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Project approval接口")
@RestController
@RequestMapping("/prj/approval")
public class ApprovalController {


    @Autowired
    private IApprovalService approvalService;

/** Project Approval*/
    /** 01.09.1 查 单个 Approval */
    @Operation(summary = "01.09.1查 单个 Approval")
    @GetMapping("/{prjCode}")
    public ApprovalVO approvalById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( approvalService.getById(prjCode) ,ApprovalVO.class);
    }

    /** 01.09.2 改 单个 Approval*/
    @Operation(summary = "01.09.2 改 单个 Approval")
    @PutMapping
    public ResponseEntity<String> updateApproval(@RequestBody ApprovalDTO dto){
        //  把DTO拷贝到PO
        ApprovalPO po = BeanUtil.copyProperties(dto,ApprovalPO.class);
        Subject subject = SecurityUtils.getSubject();
        po.setApprovalUpdater(subject.getPrincipals().toString());
        po.setApprovalUpdateTime(LocalDateTime.now());
        // 新增
        approvalService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

