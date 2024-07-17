package com.ekz.ekzweb.controller.perms;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ekz.ekzweb.domain.perms.PermsUserProjectPermission;
import com.ekz.ekzweb.service.perms.IUserProjectPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UserProjectPermission 接口")
@RestController
@RequestMapping("/userProjectPermission")
public class UserProjectPermissionController {

    @Autowired
    private IUserProjectPermissionService service;

    @Operation(summary = "getPrincipalsPerms")
    @GetMapping("/getPrincipalsPerms")
    public List<String> getPrincipalsPerms(){

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();

        return service.getPrincipalPermissions(principals);
    }


    @Operation(summary = "principalsPermsByPrjCode")
    @GetMapping("/principalsPermsByPrjCode/{prjCode}")
    public List<String> principalsPermsByPrjCode(@PathVariable String prjCode){
        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();

        return service.listObjs(
                new LambdaQueryWrapper<PermsUserProjectPermission>()
                .select(PermsUserProjectPermission::getPermission)
                .eq(PermsUserProjectPermission::getUser, principals)
                .eq(PermsUserProjectPermission::getPrjCode, prjCode)
        );

    }

    @Operation(summary = "getPrjPermsByPrjCode")
    @GetMapping("/getPrjPermsByPrjCode/{prjCode}")
    public List<PermsUserProjectPermission> getPrjPermsByProject(@PathVariable String prjCode){

        // checkPermission(prjCode+":*")
        Subject subject = SecurityUtils.getSubject();
        if (!( subject.isPermitted(prjCode+":manager") || subject.isPermitted(prjCode+":member") ) ) {
            subject.checkPermissions(prjCode+"::manager");
        }

        return service.lambdaQuery().eq( PermsUserProjectPermission::getPrjCode, prjCode ).list();
    }

    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){

        PermsUserProjectPermission userProjectPermission = service.getById(id);
        String prjCode = userProjectPermission.getPrjCode();

        // checkPermission(prjCode+":manager")
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":manager");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @Operation(summary = "增 单个")
    @PostMapping()
    public ResponseEntity<String> save(@RequestBody PermsUserProjectPermission userProjectPermission){

        String prjCode = userProjectPermission.getPrjCode();

        // checkPermission(prjCode+":manager")
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(prjCode+":manager");

        service.save(userProjectPermission);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
