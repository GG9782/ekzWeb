package com.ekz.ekzweb.controller.project.TextIssue;

import com.ekz.ekzweb.domain.project.textIssue.TextIssue;

import com.ekz.ekzweb.service.project.textIssue.ITextIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import java.nio.file.Path;

@Tag(name = "Project textIssue 接口")
@RestController
@RequestMapping("/prj/textIssue")
public class TextIssueController {

    @Autowired
    private ITextIssueService service;

/** Project TextIssue */

    private static final Path PATH = Paths.get("\\\\10.41.34.24\\e\\img\\textIssue");
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");
    private static final long MAX_FILE_SIZE = 3 * 1024 * 1024;


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
    public ResponseEntity<String> Delete(@PathVariable("id") String id) throws IOException {
        // checkPermission(prjCode+":member")
        TextIssue textIssue = service.getById(id);
        String prjCode = textIssue.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        service.removeById(id);

        String img = service.getById(id).getImg();
        Path pathToDelete = Paths.get(PATH.toString(),id + "."+ img);
        if(Files.exists(pathToDelete)){
            Files.delete(pathToDelete);
        }

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
    public ResponseEntity<String> uploadFile(@RequestParam("uploadFile") MultipartFile file, @PathVariable("id") String id) throws IOException {
        // checkPermission(prjCode+":member")
        String prjCode = service.getById(id).getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file name");
        }

        String fileName = StringUtils.cleanPath(originalFileName);

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPG, JPEG, PNG files are allowed");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds the limit of 3MB");
        }

        try {
            // 使用`Paths.get()`方法创建一个`Path`对象，表示要保存上传文件的路径。
            Path path = Paths.get(PATH.toString(), id + "." + fileExtension);

            // Check if file already exists
            String img = service.getById(id).getImg();
            Path pathToDelete = Paths.get(PATH.toString(),id + "."+ img);
            if(Files.exists(pathToDelete)){
                Files.delete(pathToDelete);
            }


            // 使用`Files.copy()`方法将用户上传文件的输入流复制到指定的目标路径。
            // `file.getInputStream()`获取用户上传文件的输入流，
            // `path`则是目标文件保存的路径。
            Files.copy(file.getInputStream(), path);
            service.lambdaUpdate().eq(TextIssue::getId,id).set(TextIssue::getImg,fileExtension).update();
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e);
        }
    }

    @Operation(summary = "依id 显示图片")
    @GetMapping("/getPictureById/{id}")
    public ResponseEntity<Resource> getPrincipalsImage( @PathVariable String id ) {

        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        try {
            Path path = Paths.get(PATH.toString(),id + ".jpg");
            Resource resource = new UrlResource(path.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/png") // 设置图片格式
                    .body(resource);
        } catch (MalformedURLException e) {
            // 处理 MalformedURLException 异常
            return ResponseEntity.notFound().build();
        }
    }

}

