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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.nio.file.Path;

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
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

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
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

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
        // checkPermission(prjCode+":member")
        TextIssue textIssue = service.getById(id);
        String prjCode = textIssue.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody TextIssue textIssue){

        // checkPermission(prjCode+":member")
        String prjCode = textIssue.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

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
        // checkPermission(prjCode+":member")
        String prjCode = textIssue.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

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
        // check prjCodes is equals
        if (textIssueList == null || textIssueList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TextIssueList can't be empty");
        }

        String prjCode = textIssueList.get(0).getPrjCode();
        for (TextIssue textIssue : textIssueList) {
            if (!prjCode.equals(textIssue.getPrjCode())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All prjCodes need to be equals");
            }
        }

        // checkPermission(prjCode+":member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        String principals = subject.getPrincipals().toString();
        for (TextIssue textIssue : textIssueList) {
            textIssue.setCreator(principals);
            textIssue.setCreateTime(LocalDateTime.now());
        }
        service.saveBatch(textIssueList);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
    @Operation(summary = "上传图片")
    @PostMapping("/uploadPicture/{id}")
    public ResponseEntity<String> uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile, @PathVariable("id") String id) throws IOException {
        Path path = Paths.get("D:/0710");

        // 首先判断上传的文件是否为空
        if (!multipartFile.isEmpty()) {
            String suffix = ".unknown"; // 初始文件后缀为不知道
            String name = multipartFile.getOriginalFilename(); // 获取上传的文件名

            // 获取文件的后缀
            if (name != null && name.contains(".")) {
                suffix = name.substring(name.lastIndexOf("."));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file name!");
            }

            String dest = UUID.randomUUID() + suffix; // 生成保存的文件名

            // 保存文件到指定位置
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, path.resolve(dest), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file!");
            }

            // 更新数据库中的图片路径
            service.lambdaUpdate().set(TextIssue::getPicture, path.resolve(dest).toString()).eq(TextIssue::getId, id).update();

            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty!");
    }

}

