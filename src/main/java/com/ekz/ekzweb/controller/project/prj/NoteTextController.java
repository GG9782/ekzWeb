package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.MemberDTO;
import com.ekz.ekzweb.domain.project.prj.po.MemberPO;
import com.ekz.ekzweb.domain.project.prj.po.NoteTextPO;
import com.ekz.ekzweb.domain.project.prj.vo.MemberVO;
import com.ekz.ekzweb.service.project.prj.INoteTextService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Project NoteText接口")
@RestController
@RequestMapping("/prj/noteText")
public class NoteTextController {
    @Autowired
    private INoteTextService service;

    @Operation(summary = "查 单个")
    @GetMapping("/{prjCode}")
    public NoteTextPO getNoteTextById(@PathVariable("prjCode") String prjCode) {
        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.getById(prjCode);
    }

    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateNoteTextById(@RequestBody NoteTextPO po){
        // checkPermission(prjCode+":member")
        String prjCode = po.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        po.setNoteUpdater(subject.getPrincipals().toString());
        po.setNoteUpdateTime(LocalDateTime.now());

        // 新增
        service.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
