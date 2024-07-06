package com.ekz.ekzweb.controller.perms;


import com.ekz.ekzweb.service.perms.IUserProjectPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserUserProjectPermission 接口")
@RestController
@RequestMapping("/userProjectPermission")
public class UserProjectPermissionController {

    @Autowired
    private IUserProjectPermissionService service;




}
