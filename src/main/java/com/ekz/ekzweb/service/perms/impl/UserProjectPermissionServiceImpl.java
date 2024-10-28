package com.ekz.ekzweb.service.perms.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.perms.PermsUserProjectPermission;
import com.ekz.ekzweb.mapper.perms.UserProjectPermissionMapper;
import com.ekz.ekzweb.service.perms.IUserProjectPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProjectPermissionServiceImpl extends ServiceImpl<UserProjectPermissionMapper, PermsUserProjectPermission> implements IUserProjectPermissionService {
    @Autowired
    private UserProjectPermissionMapper mapper;

    public List<String> getPrincipalPermissions(String principal){
        return listObjs( new LambdaQueryWrapper<PermsUserProjectPermission>().select(PermsUserProjectPermission::getPermission).eq(PermsUserProjectPermission::getUser, principal) );
    }

}
