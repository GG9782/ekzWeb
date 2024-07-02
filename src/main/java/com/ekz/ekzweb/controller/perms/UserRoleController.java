package com.ekz.ekzweb.controller.perms;


import com.ekz.ekzweb.service.perms.IRolePermissionService;
import com.ekz.ekzweb.service.perms.IUserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "UserRole 接口")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private IUserRoleService service;
    @Autowired
    private IRolePermissionService rolePermissionService;

    @Operation(summary = "getPrincipalsRoles")
    @GetMapping("/getPrincipalsRoles")
    public List<String> getPrincipalsRoles(){
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        return service.getUserRoles(principals);
    }

    @Operation(summary = "getPrincipalsPermission")
    @GetMapping("/getPrincipalsPermission")
    public List<String> getPrincipalsPermission(){
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        return rolePermissionService.getPermissionsByRoles(service.getUserRoles(principals));
    }

}
