package com.ekz.ekzweb.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ekz.ekzweb.domain.perms.PermsRolePermission;
import com.ekz.ekzweb.domain.perms.PermsUserRole;
import com.ekz.ekzweb.domain.standardValue.StdCustomer;
import com.ekz.ekzweb.domain.standardValue.StdProductType;
import com.ekz.ekzweb.domain.user.User;
import com.ekz.ekzweb.service.perms.IRolePermissionService;
import com.ekz.ekzweb.service.perms.IRoleService;
import com.ekz.ekzweb.service.perms.IUserRoleService;
import com.ekz.ekzweb.service.user.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;
import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRolePermissionService rolePermissionService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("star Authorization");
        //获取当前用户身份信息
        String principal = principalCollection.getPrimaryPrincipal().toString();
        //调用接口方法获取用户的角色信息
        List<String> roles = userRoleService.getUserRoles(principal);
        System.out.println("当前用户角色信息："+roles);
        //调用接口方法获取用户角色的权限信息
        List<String> permissions = rolePermissionService.getPermissionsByRoles(roles);
        System.out.println("当前用户权限信息："+permissions);
        //创建对象，存储当前登录的用户的权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //存储角色
        info.addRoles(roles);
        //存储权限信息
        info.addStringPermissions(permissions);
        //返回
        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("star Authentication");

        //获取用户身份信息
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

