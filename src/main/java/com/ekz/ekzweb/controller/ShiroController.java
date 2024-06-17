package com.ekz.ekzweb.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shiro")
public class ShiroController {
//    @Autowired
//    private IShiroService shiroService;
//
//    /**
//     * 登陆页面跳转
//     */
////    @GetMapping("/login")
////    public String login(){
////        return "login";
////    }
//
//    /**
//     * 登录逻辑处理
//     */
//    @Operation(summary = "login")
//    @GetMapping("/login")
//    @ResponseBody
//    public String userLogin(String name,String pwd){
//        //1 获取 Subject 对象
//        Subject subject = SecurityUtils.getSubject();
//        //2 封装请求数据到 token 对象中
//        AuthenticationToken token = new UsernamePasswordToken(name,pwd);
//        //3 调用 login 方法进行登录认证
//        try {
//            subject.login(token);
//            return "login success";
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            System.out.println("login fail");
//            return "登录失败";
//        }
//    }

//    @Operation(summary = "根据id查询用户接口")
//    @GetMapping("/shiro/{id}")
//    public ShiroPo getUserInfoById(@Parameter(name = "用户id") @PathVariable("id") String id){
//        ShiroPo shiroPo = IShiroService.getUserInfoById(id);
//        return shiroPo;
//    }

}
