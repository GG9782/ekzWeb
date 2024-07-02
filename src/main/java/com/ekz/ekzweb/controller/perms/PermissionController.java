package com.ekz.ekzweb.controller.perms;

import com.ekz.ekzweb.service.perms.IPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Permission 接口")
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService service;



}
