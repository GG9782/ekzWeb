package com.ekz.ekzweb.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
/**
 * @Description:shiro配置类
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private UserRealm userRealm;

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/users/adLogin");


        // 设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/users/adLogin");

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/users/logout", "logout");
        filterMap.put("/users/logout", "anon");
        filterMap.put("/users/adLogin", "anon");
        filterMap.put("/doc.html/**", "anon");
//        filterMap.put("/**", "anon");

//        filterMap.put("/shiro/hello", "anon");
//
//        filterMap.put("/login", "anon");

        filterMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //1 创建 securityManager 对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //2 将 myRealm 存入 securityManager 对象
        securityManager.setRealm(userRealm);
        //3 返回
        return securityManager;
    }

}