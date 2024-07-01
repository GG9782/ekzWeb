package com.ekz.ekzweb.service.perms.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.perms.PermsPermission;
import com.ekz.ekzweb.mapper.perms.PermissionMapper;
import com.ekz.ekzweb.service.perms.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermsPermission> implements IPermissionService {
    @Autowired
    private PermissionMapper mapper;
}
