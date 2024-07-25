package com.ekz.ekzweb.controller.user;


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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        PermsUserRole permsUserRole = new PermsUserRole();
        permsUserRole.setUser(user.getEmail());
        permsUserRole.setRole("member");
        // 新增
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
}
