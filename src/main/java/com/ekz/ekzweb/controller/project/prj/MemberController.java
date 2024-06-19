package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.MemberDTO;
import com.ekz.ekzweb.domain.project.prj.po.MemberPO;
import com.ekz.ekzweb.domain.project.prj.vo.MemberVO;
import com.ekz.ekzweb.service.project.prj.IMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Project member接口")
@RestController
@RequestMapping("/prj/member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

/** Project Member*/

    /** 01.05.1 查 单个 Project Member*/
    @Operation(summary = "01.05.1 查 单个 Member")
    @GetMapping("/{prjCode}")
    public MemberVO getMemberById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( memberService.getById(prjCode) ,MemberVO.class);
    }

    /** 01.05.2 改 单个 Project Member*/
    @Operation(summary = "01.05.2 改 单个 Member")
    @PutMapping
    public ResponseEntity<String> updateMember(@RequestBody MemberDTO dto){
        //  把DTO拷贝到PO
        MemberPO po = BeanUtil.copyProperties(dto,MemberPO.class);
        Subject subject = SecurityUtils.getSubject();
        po.setMemberUpdater(subject.getPrincipals().toString());
        po.setMemberUpdateTime(LocalDateTime.now());
        // 新增
        memberService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

