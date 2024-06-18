package com.ekz.ekzweb.controller.user;


import com.ekz.ekzweb.domain.user.User;
import com.ekz.ekzweb.service.user.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
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

    /**
     * 登录逻辑处理
     */
    @GetMapping("/login")
    @ResponseBody
    public String userLogin(String id,String pwd){
        //1 获取 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        //2 封装请求数据到 token 对象中
        AuthenticationToken token = new
                UsernamePasswordToken(id,pwd);
        //3 调用 login 方法进行登录认证
        try {
            subject.login(token);
            return "login success " + subject.getPrincipals().toString();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("login fail");
            return "登录失败";
        }
    }

    @Operation(summary = "adLogin")
    @GetMapping("/adLogin")
    @ResponseBody
    public String adLogin(String email, String password){
        if(email.contains("@")){
            User user = userService.getById(email);
            email = user.getEmail();
        }
        return userService.adLogin(email,password);
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> userLogout(){
        SecurityUtils.getSubject().logout();
        return ResponseEntity.status(HttpStatus.OK).body("Logout OK");
    }

    @GetMapping("/getPrincipals")
    @ResponseBody
    public ResponseEntity<String> getPrincipals(){
        Subject subject = SecurityUtils.getSubject();
        if( subject.getPrincipals() == null ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }
        String principals = subject.getPrincipals().toString();
        return ResponseEntity.status(HttpStatus.OK).body(principals);
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

    @Operation(summary = "根据id查询用户接口")
    @GetMapping("{id}")
    public User queryUserById(@Parameter(name = "用户id") @PathVariable("id") String id){
        User userPO = userService.getById(id);
        return userPO;
    }


//
//    @Operation(summary = "根据id批量查询用户接口")
//    @GetMapping
//    public List<UserVO> queryUserByIds(@Parameter(name ="用户id集合") @RequestParam("ids") List<Long> ids){
//        List<User> users = userService.listByIds(ids);
//        return BeanUtil.copyToList(users,UserVO.class);
//    }
//
    @Operation(summary = "根据id修改用户接口")
    @PutMapping
    public void updateUser(@RequestBody User user){
        user.setPwd(null);
        userService.updateById(user);
    }

    @Operation(summary = "修改当前用户密码")
    @PutMapping("/{pwd}")
    public ResponseEntity<String> updatePwd(@PathVariable String pwd){
        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        String encryptedPassword = new SimpleHash("MD5", pwd, "salt", 3).toString();

        userService.lambdaUpdate()
                .eq(User::getId,principals)
                .set(User::getPwd,encryptedPassword)
                .update();
        return  ResponseEntity.status(HttpStatus.OK).body("OK");

    }
//
//    @Operation(summary = "根据id修改用户接口")
//    @PutMapping("/lambdaUpdate")
//    public void lambdaUpdateUser(@RequestBody User user){
//
//        userService.save(user);
//    }

}
