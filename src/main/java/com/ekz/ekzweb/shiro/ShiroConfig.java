package com.ekz.ekzweb.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashMap;
/**
 * @Description:shiro配置类
 */
@Configuration
@EnableAspectJAutoProxy
public class ShiroConfig {

    @Autowired
    private UserRealm userRealm;

    /**
     * 创建ShiroFilterFactoryBean
     */
//    @Bean(name = "filterShiroFilterRegistrationBean")
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置未登录跳转页面
        shiroFilterFactoryBean.setLoginUrl("/users/unauthorized");

        // 设置未授权跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/users/getPrincipals");

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
        filterMap.put("/users/unauthorized", "anon");
        filterMap.put("/users/adLogin", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
//
//    @Bean(name = "filterShiroFilterRegistrationBean")
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        // need to accept POSTs from the login form
//        chainDefinition.addPathDefinition("/**", "authc");
//        chainDefinition.addPathDefinition("/users/logout", "logout");
//        return chainDefinition;
//    }

    /**
     * 创建DefaultWebSecurityManager
     */

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){

        //1 创建 securityManager 对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //2 将 myRealm 存入 securityManager 对象
        securityManager.setRealm(userRealm);
//        ThreadContext.bind(securityManager);
        //3 返回
        return securityManager;
    }

}