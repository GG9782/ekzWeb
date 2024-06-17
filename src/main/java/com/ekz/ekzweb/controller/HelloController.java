package com.ekz.ekzweb.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    /**
     * 测试方法
     */

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello");
        String id = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(id);
        return "hello";

    }
    /**
     * 测试方法
     */

    @GetMapping("/Authenticate")
    @ResponseBody
    public String helloAuthenticated(){
        System.out.println("hello authenticated User");
        return "hello authenticated User";
    }

}
