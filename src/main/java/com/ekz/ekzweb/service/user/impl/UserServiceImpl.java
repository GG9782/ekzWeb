package com.ekz.ekzweb.service.user.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.user.User;
import com.ekz.ekzweb.mapper.user.UserMapper;
import com.ekz.ekzweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoByName(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    /**
     * LDAP 验证密码
     *
     * @param email    邮箱
     * @param password 密码
     */
    @Override
    public String adLogin(String email, String password){
        Hashtable hashEnv = new Hashtable();
        try {
            // LDAP访问安全级别
            hashEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
            // AD User
            hashEnv.put(Context.SECURITY_PRINCIPAL, email);
            // AD Password
            hashEnv.put(Context.SECURITY_CREDENTIALS, password);
            // LDAP工厂类
            hashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            // LDAP URL
            hashEnv.put(Context.PROVIDER_URL, "ldap://10.41.20.20:389");
            //使用用户输入的OA账号密码去连接LDAP服务器，验证能否成功
            LdapContext ctx = new InitialLdapContext(hashEnv, null);
            ctx.close();
        } catch (Exception e) {
            System.out.println(e);
            return "fail";
//            logger.error("LDAP sign in fail =>{}", e.getMessage());
        }
        return "ok";
    }

}
