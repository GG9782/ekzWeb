package com.ekz.ekzweb.controller.user;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ekz.ekzweb.domain.perms.PermsUserRole;
import com.ekz.ekzweb.domain.user.User;
import com.ekz.ekzweb.service.perms.IUserRoleService;
import com.ekz.ekzweb.service.user.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@Tag(name = "User 接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService service;
    @Autowired
    private IUserRoleService userRoleService;

    @Operation(summary = "adLogin")
    @GetMapping("/adLogin")
    @ResponseBody
    public String adLogin(String username, String password){
        if(!username.contains("@")){
            User user = service.lambdaQuery().select(User::getEmail).eq(User::getUserId,username).one();
            if(user == null){
                return "id "+username+" is not recorded by ekzWeb system.";
            }
            username = user.getEmail();
        }
        //1 获取 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        //2 封装请求数据到 token 对象中
        AuthenticationToken token = new
                UsernamePasswordToken(username,password);
        //3 调用 login 方法进行登录认证
        try {
            subject.login(token);
            return "login success " + subject.getPrincipals().toString();
        }catch (AuthenticationException e) {
            return "Login fail！Incorrect username or password.";
        }
    }

    @Operation(summary = "logout")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> userLogout(){
        SecurityUtils.getSubject().logout();
        return ResponseEntity.status(HttpStatus.OK).body("Logout OK");
    }


    @Operation(summary = "获取当前用户")
    @GetMapping("/getPrincipals")
    @ResponseBody
    public ResponseEntity<String> getPrincipals(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            String principals = subject.getPrincipals().toString();
            return ResponseEntity.status(HttpStatus.OK).body("principals " + principals );
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
    }

    @Operation(summary = "新增")
    @PostMapping
    public void saveUser(@RequestBody User user){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        // 新增
        PermsUserRole permsUserRole = new PermsUserRole();
        permsUserRole.setUser(user.getEmail());
        permsUserRole.setRole("member");

        service.save(user);
        userRoleService.save(permsUserRole);

    }

    @Operation(summary = "根据email删除")
    @DeleteMapping("/{email}")
    public void deleteUserByEmail(@PathVariable("email") String email){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");
        service.removeById(email);
    }

    @Operation(summary = "根据email查询")
    @GetMapping("/{email}")
    public User queryUserByEmail(@PathVariable String email){
        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.getById(email);
    }

//    @Operation(summary = "多条件查询")
//    @GetMapping("/query")
//    public List<User> queryUser(@PathVariable User user){
//        // checkRole("member")
//        Subject subject = SecurityUtils.getSubject();
//        subject.checkRole("member");
//
//        return service.lambdaQuery(user).list();
//    }

    @Operation(summary = "get all")
    @GetMapping("/all")
    public List<User> getAllUser(){
        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return service.list();
    }

    @Operation(summary = "根据email修改")
    @PutMapping
    public void updateUser(@RequestBody User user){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");
        service.updateById(user);
    }
    private static final Path userImgPath = Paths.get("\\\\10.41.34.24\\e\\img\\user");
    private static final List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
    private static final long MAX_FILE_SIZE = 512 * 1024; // 512KB
    @Operation(summary = "上传头像")
    @PostMapping("/images/{email}")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile file,@PathVariable String email) {
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file name");
        }

        String fileName = StringUtils.cleanPath(originalFileName);

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!allowedExtensions.contains(fileExtension)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPG, JPEG, PNG, GIF files are allowed");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds the limit of 512KB");
        }

        try {
            // 使用`Paths.get()`方法创建一个`Path`对象，表示要保存上传文件的路径。
            // `uploadPath`是要保存文件的目标文件夹路径，
            // `File.separator`是文件分隔符（在不同操作系统上可能不同），
            // `fileName`是上传文件的文件名。
//            Path path = userImgPath.resolve(File.separator + email + "." + fileExtension);
            Path path = Paths.get(userImgPath.toString(), email + "." + fileExtension);

            // Check if file already exists
            if (Files.exists(path)) {
                Files.delete(path); // Delete existing file
            }

            // 使用`Files.copy()`方法将用户上传文件的输入流复制到指定的目标路径。
            // `file.getInputStream()`获取用户上传文件的输入流，
            // `path`则是目标文件保存的路径。
            Files.copy(file.getInputStream(), path);
            service.lambdaUpdate().eq(User::getEmail,email).set(User::getImg,path.toString()).update();
            return ResponseEntity.ok("File uploaded successfully: " + path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e);
        }
    }

    @Operation(summary = "显示当前用户头像")
    @GetMapping("/images/principals")
    public ResponseEntity<Resource> getPrincipalsImage() {
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        try {
            String img = service.getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, principals).select(User::getImg)).getImg();
            Path path = Paths.get(img);
            Resource resource = new UrlResource(path.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/png") // 设置图片格式
                    .body(resource);
        } catch (MalformedURLException e) {
            // 处理 MalformedURLException 异常
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "显示指定用户头像")
    @GetMapping("/images/{email}")
    public ResponseEntity<Resource> getImageByEmail(@PathVariable String email) {
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        try {
            String img = service.getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, email).select(User::getImg)).getImg();
            Path path = Paths.get(img);
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
