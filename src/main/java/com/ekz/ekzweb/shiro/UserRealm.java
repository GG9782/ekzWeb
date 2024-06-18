package com.ekz.ekzweb.shiro;

import com.ekz.ekzweb.domain.user.User;
import com.ekz.ekzweb.service.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

@Component
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private IUserService userService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("star Authorization");
        return null;
    }
    /**
     * 执行认证逻辑
     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
//        System.out.println("star Authentication");
//
//        //1 获取用户身份信息
//        String name = token.getPrincipal().toString();
//        System.out.println(name);
//        //2 调用业务层获取用户信息（数据库中）
//        User user = userService.getUserInfoByName(name);
//        //3 判断并将数据完成封装
//        if(user!=null){
//            AuthenticationInfo info = new SimpleAuthenticationInfo(
//                    token.getPrincipal(),
//                    user.getPwd(),
//                    ByteSource.Util.bytes("salt"),
//                    token.getPrincipal().toString()
//            );
//            return info;
//        }
//
//        return null;
//    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());

        // 构建LDAP连接配置
        Hashtable<String, String> hashEnv = new Hashtable<>();
        hashEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        // AD User
        hashEnv.put(Context.SECURITY_PRINCIPAL, username);
        // AD Password
        hashEnv.put(Context.SECURITY_CREDENTIALS, password);
        // LDAP工厂类
        hashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // LDAP URL
        hashEnv.put(Context.PROVIDER_URL, "ldap://10.41.20.20:389");
        //使用用户输入的OA账号密码去连接LDAP服务器，验证能否成功
        try {
            // 连接LDAP服务器
            LdapContext ctx = new InitialLdapContext(hashEnv, null);

            // 如果连接成功，说明身份验证通过
            ctx.close();
            return new SimpleAuthenticationInfo(username, password, getName());
        } catch (javax.naming.AuthenticationException e) {
            throw new AuthenticationException("Login fail！Incorrect username or password.");
        }catch (javax.naming.CommunicationException e) {
            throw new AuthenticationException("LDAP connection timeout. Please check your network.");
        } catch (Exception e) {
            throw new AuthenticationException("Login fail！Please check network or password.");
        }
    }

}

