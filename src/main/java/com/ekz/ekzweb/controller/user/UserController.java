package com.ekz.ekzweb.controller.user;


import com.ekz.ekzweb.domain.user.User;
import com.ekz.ekzweb.service.user.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@Tag(name = "用户管理接口")
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
//    @RequiresRoles("admin")
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

//    @Operation(summary = "新增用户接口")
//    @PostMapping
//    public void saveUser(@RequestBody UserFormDTO userDTO){
//        //  把DTO拷贝到PO
//        User user = BeanUtil.copyProperties(userDTO,User.class);
//        // 新增
//        userService.save(user);
//    }
//
//    @Operation(summary = "删除用户接口")
//    @DeleteMapping("{id}")
//    public void deleteUserById(@Parameter(name = "用户id") @PathVariable("id") Long id){
//        userService.removeById(id);
//    }

//    @Operation(summary = "根据id查询用户")
//    @GetMapping("{id}")
//    public User queryUserById(@Parameter(name = "用户id") @PathVariable("id") String id){
//        User userPO = userService.getById(id);
//        return userPO;
//    }

//    @Operation(summary = "根据id批量查询用户接口")
//    @GetMapping
//    public List<UserVO> queryUserByIds(@Parameter(name ="用户id集合") @RequestParam("ids") List<Long> ids){
//        List<User> users = userService.listByIds(ids);
//        return BeanUtil.copyToList(users,UserVO.class);
//    }

//    @Operation(summary = "根据id修改用户")
//    @PutMapping
//    public void updateUser(@RequestBody User user){
//        user.setPwd(null);
//        userService.updateById(user);
//    }
}
