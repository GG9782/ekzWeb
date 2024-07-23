package com.ekz.ekzweb.controller.user;


import com.ekz.ekzweb.domain.user.User;
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
    private IUserService userService;

    @Operation(summary = "adLogin")
    @GetMapping("/adLogin")
    @ResponseBody
    public String adLogin(String username, String password){
        if(!username.contains("@")){
            User user = userService.lambdaQuery().select(User::getEmail).eq(User::getUserId,username).one();
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
        userService.save(user);
    }

    @Operation(summary = "根据email删除")
    @DeleteMapping("/{email}")
    public void deleteUserByEmail(@PathVariable("email") String email){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");
        userService.removeById(email);
    }

    @Operation(summary = "根据email查询")
    @GetMapping("/{email}")
    public User queryUserByEmail(@PathVariable("email") String email){
        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        return userService.getById(email);
    }

    @Operation(summary = "多条件查询")
    @GetMapping("/query")
    public List<User> queryUser(@RequestBody User user){
        // checkRole("member")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        if(user == null){
            // 如果 user 为 null，则直接查询整个表的数据
            return userService.list();
        } else {
            // 如果 user 不为 null，则根据 user 对象构建查询条件
            return userService.lambdaQuery(user).list();
        }
    }

    @Operation(summary = "根据email修改")
    @PutMapping
    public void updateUser(@RequestBody User user){
        // checkRole("admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");
        userService.updateById(user);
    }
}
