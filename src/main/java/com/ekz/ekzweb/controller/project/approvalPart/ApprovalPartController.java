package com.ekz.ekzweb.controller.project.approvalPart;

import com.ekz.ekzweb.domain.project.approvalPart.ApprovalPart;
import com.ekz.ekzweb.service.project.approvalPart.IApprovalPartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "Project ApprovalPart 接口")
@RestController
@RequestMapping("/prj/approvalPart")
public class ApprovalPartController {

    @Autowired
    private IApprovalPartService service;

    private static final Path PATH = Paths.get("\\\\10.41.34.24\\e\\files\\approvalPart");
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("xls","xlsx", "xlsm");
    private static final long MAX_FILE_SIZE = 50* 1024 * 1024; // 20MB

    /** 依 prjCode 查*/
    @Operation(summary = "依 prjCode 查")
    @GetMapping("/getByPrjCode/{prjCode}")
    public List<ApprovalPart> getByPrjCode(@PathVariable String prjCode) {
        return service.lambdaQuery()
                .eq(ApprovalPart::getPrjCode,prjCode)
                .list();
    }

    /** 依 id 查*/
    @Operation(summary = "依 id 查")
    @GetMapping("/getById/{id}")
    public ApprovalPart getByPrjId(@PathVariable String id) {
        return service.getById(id);
    }


    /** 依 id 下载*/
    @Operation(summary = "依 id 下载")
    @GetMapping("/downloadById/{faiOrCpk}/{id}")
    public ResponseEntity<Resource> downloadById(@PathVariable String faiOrCpk,@PathVariable String id) {
        ApprovalPart po= service.getById(id);

        String fileName = switch (faiOrCpk) {
            case "fai" -> po.getFaiFileName();
            case "cpk" -> po.getCpkFileName();
            default -> "faiOrCpkPathVariableException";
        };

        Path filePath = Paths.get(PATH.toString(), fileName);
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


    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteById(@PathVariable("id") String id){
        // checkPermission(prjCode+":member")
        ApprovalPart po = service.getById(id);
        String prjCode = po.getPrjCode();
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        String faiFileName = po.getFaiFileName();
        String cpkFileName = po.getCpkFileName();

        try {
            if(faiFileName != null ){
                Files.delete( Paths.get(PATH.toString(), faiFileName) );
                if(cpkFileName != null ){
                    if(!faiFileName.equals(cpkFileName) ){
                        Files.delete( Paths.get(PATH.toString(), cpkFileName) );
                    }
                }
            }

            service.removeById(id);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the file: " + e);
        }
    }

    @Operation(summary = "删 批量")
    @DeleteMapping("/removeBatchByIds")
    public ResponseEntity<String> removeBatchByIds(@RequestBody List<String> ids){
        // checkPermission(prjCode+":member")
        Subject subject = SecurityUtils.getSubject();
        List<String> okList = new ArrayList<>();
        for(String id : ids){
            ApprovalPart po = service.getById(id);
            String prjCode = po.getPrjCode();

            subject.checkPermission(prjCode+":member");

            String faiFileName = po.getFaiFileName();
            String cpkFileName = po.getCpkFileName();




            try {
                if(faiFileName != null ){
                    Files.delete( Paths.get(PATH.toString(), faiFileName) );
                    if(cpkFileName != null){
                        if(!faiFileName.equals(cpkFileName) ){
                            Files.delete( Paths.get(PATH.toString(), cpkFileName) );
                        }
                    }
                }else if(cpkFileName != null){
                    Files.delete( Paths.get(PATH.toString(), cpkFileName) );
                }

                service.removeById(id);

                okList.add(po.getPartNumber() + " T" + po.getToolingStage().toString());

            } catch (IOException e) {
                // 使用Java 8 Stream API来计算 ids 排除 okList 后的结果
//                List<String> failList = ids.stream()
//                        .filter(item -> !okList.contains(item)).toList(); // 收集结果到新的列表中
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("succeed to delete the file: " + okList + "\n" + "But failed to delete others" + "\n"+ "IOException: " + e);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增或改 单个 */
    @Operation(summary = "增或改 单个")
    @PostMapping
    public ResponseEntity<String> save(String faiOrCpkOrBoth,
                                       @RequestParam(required = false) String id,
                                       String prjCode,
                                       String partNumber,
                                       String partName,
                                       String partType,
                                       Integer toolingStage,
                                       @RequestParam(required = false) Integer faiAccept,
                                       @RequestParam(required = false) Integer faiAlert,
                                       @RequestParam(required = false) Integer faiReject,
                                       @RequestParam(required = false) Integer cpkAccept,
                                       @RequestParam(required = false) Integer cpkAlert,
                                       @RequestParam(required = false) Integer cpkReject,
                                       @RequestParam("file") MultipartFile file){
        // checkPermission(prjCode+":member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":member");

        //文件处理
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file name");
        }

        String cleanFileName = StringUtils.cleanPath(originalFileName);

        String fileExtension = cleanFileName.substring(cleanFileName.lastIndexOf(".") + 1).toLowerCase();

        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only xls, xlsx, xlsm files are allowed");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds the limit of 50MB, current file size is: "+ file.getSize() );
        }

        //PO处理
        if(id != null){
        if(id.equals("")){
            id = null;
        }}
        ApprovalPart po =new ApprovalPart();
        po.setId(id);
        po.setPrjCode(prjCode);
        po.setPartNumber(partNumber);
        po.setPartName(partName);
        po.setPartType(partType);
        po.setToolingStage(toolingStage);

        po.setFaiAccept(faiAccept);
        po.setFaiAlert(faiAlert);
        po.setFaiReject(faiReject);

        po.setCpkAccept(cpkAccept);
        po.setCpkAlert(cpkAlert);
        po.setCpkReject(cpkReject);

        String principals = subject.getPrincipals().toString();
        po.setCreator(principals);
        po.setCreateTime(LocalDateTime.now());

        String newFileName = po.getPrjCode() + po.getToolingStage() +"_" + po.getPartType() + "_T" + toolingStage + "_" + po.getPartNumber();
        switch (faiOrCpkOrBoth) {
            case "fai":
                newFileName = StringUtils.cleanPath( newFileName + "_fai." + fileExtension);
                po.setFaiFileName(newFileName);
                po.setCpkAccept(null);
                po.setCpkAlert(null);
                po.setCpkReject(null);
                po.setCpkFileName(null);
                break;
            case "cpk":
                newFileName = StringUtils.cleanPath( newFileName + "_cpk." + fileExtension);
                po.setCpkFileName(newFileName);
                po.setFaiAccept(null);
                po.setFaiAlert(null);
                po.setFaiReject(null);
                po.setFaiFileName(null);
                break;
            case "both":
                newFileName = StringUtils.cleanPath( newFileName + "_fai_cpk." + fileExtension);
                po.setFaiFileName(newFileName);
                po.setCpkFileName(newFileName);
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("faiOrCpkOrBoth PathVariable Exception");
        }

        //数据库处理
        service.saveOrUpdate(po);

        //文件保存
        try {
            //如果是修改，就先删除旧的文件
            if(id != null){
                String currentFileName;
                if(faiOrCpkOrBoth.equals("fai")){
                    currentFileName = service.getById(id).getFaiFileName();
                }else {
                    currentFileName = service.getById(id).getCpkFileName();
                }

                Path currentFilePath = Paths.get(PATH.toString(), currentFileName);
                if (Files.exists(currentFilePath)) {
                    Files.delete(currentFilePath); // Delete existing file
                }
            }

            // 使用`Paths.get()`方法创建一个`Path`对象，表示要保存上传文件的路径。
            Path path = Paths.get(PATH.toString(), newFileName);

            // Check if file already exists
            if (Files.exists(path)) {
                Files.delete(path); // Delete existing file
            }

            // 使用`Files.copy()`方法将用户上传文件的输入流复制到指定的目标路径。
            // `file.getInputStream()`获取用户上传文件的输入流，
            // `path`则是目标文件保存的路径。
            Files.copy(file.getInputStream(), path);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e);
        }
            return ResponseEntity.ok("File uploaded & data update successfully");
    }


}

