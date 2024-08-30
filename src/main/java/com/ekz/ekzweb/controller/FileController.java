package com.ekz.ekzweb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


@Tag(name = "通用文件处理 接口")
@RestController
@RequestMapping("/file")
public class FileController {
    @Operation(summary = "显示图片")
    @GetMapping("/images/{imagePath}")
    public ResponseEntity<Resource> getImage(@PathVariable Path imagePath) {
        try {
            Resource resource = new UrlResource(imagePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/png") // 设置图片格式
                    .body(resource);
        } catch (MalformedURLException e) {
            // 处理 MalformedURLException 异常
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "下载文件")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        String FILE_DIRECTORY = "D:\\0710";
        Path filePath = Paths.get(FILE_DIRECTORY, fileName);
        try {
            Resource resource = new org.springframework.core.io.UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


}
