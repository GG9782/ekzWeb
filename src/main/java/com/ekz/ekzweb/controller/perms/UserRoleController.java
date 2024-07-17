package com.ekz.ekzweb.controller.perms;


import com.ekz.ekzweb.domain.perms.PermsUserRole;
import com.ekz.ekzweb.service.perms.IRolePermissionService;
import com.ekz.ekzweb.service.perms.IUserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return service.getPrincipalRoles(principals);
    }

    @Operation(summary = "getPrincipalsPermission")
    @GetMapping("/getPrincipalsPermissions")
    public List<String> getPrincipalsPermissions(){
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        return rolePermissionService.getPermissionsByRoles(service.getPrincipalRoles(principals));
    }

    @Operation(summary = "getRolesByUser")
    @GetMapping("/getRolesByUser/{userName}")
    public List<PermsUserRole> getRolesByUser(@PathVariable String userName){
        // checkRole(admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        return service.lambdaQuery().like( PermsUserRole::getUser, userName.trim().replace(" ", "_") ).list();
    }

    @Operation(summary = "getUserByRole")
    @GetMapping("/getUserByRole/{role}")
    public List<PermsUserRole> getUserByRole(@PathVariable String role){
        // checkRole(admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        return service.lambdaQuery().eq(PermsUserRole::getRole,role).list();
    }

    @Operation(summary = "getAll")
    @GetMapping("/getAll")
    public List<PermsUserRole> getAll(){
        // checkRole(admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        return service.list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        // checkRole(admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping()
    public ResponseEntity<String> save(@RequestBody PermsUserRole userRole){
        // checkRole(admin")
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");

        service.save(userRole);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
