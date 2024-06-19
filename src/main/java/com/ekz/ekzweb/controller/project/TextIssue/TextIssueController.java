package com.ekz.ekzweb.controller.project.TextIssue;

import com.ekz.ekzweb.domain.project.textIssue.TextIssue;

import com.ekz.ekzweb.service.project.textIssue.ITextIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Project textIssue 接口")
@RestController
@RequestMapping("/prj/textIssue")
public class TextIssueController {

    @Autowired
    private ITextIssueService service;

/** Project TextIssue */

    /** 查 单个*/
    @Operation(summary = "依 prjCode 查")
    @GetMapping("/{prjCode}")
    public List<TextIssue> getByPrjCode(@PathVariable("prjCode") String prjCode) {
        return service.lambdaQuery()
                .eq(TextIssue::getPrjCode,prjCode)
                .orderByDesc(TextIssue::getIsTop)
                .orderByAsc(TextIssue::getRanking)
                .list();
    }

    /** 查 top5 单个*/
    @Operation(summary = "依 prjCode 查top5")
    @GetMapping("/top5/{prjCode}")
    public List<TextIssue> getTop5ByPrjCode(@PathVariable("prjCode") String prjCode) {
        return service.lambdaQuery()
                .eq(TextIssue::getPrjCode,prjCode)
                .eq(TextIssue::getIsTop,true)
                .orderByAsc(TextIssue::getRanking)
                .list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable("id") String id){
        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody TextIssue textIssue){

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        textIssue.setCreator(principals);
        textIssue.setCreateTime(LocalDateTime.now());
        service.save(textIssue);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个")
    @PutMapping
    public ResponseEntity<String> updateById(@RequestBody TextIssue textIssue){

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        textIssue.setCreator(principals);
        textIssue.setCreateTime(LocalDateTime.now());
        service.updateById(textIssue);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增  批量*/
    @Operation(summary = "增  批量")
    @PostMapping("/list")
    public ResponseEntity<String> save(@RequestBody List<TextIssue> textIssueList){

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        for (TextIssue textIssue : textIssueList) {
            textIssue.setCreator(principals);
            textIssue.setCreateTime(LocalDateTime.now());
        }
        service.saveBatch(textIssueList);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


}

