package com.ekz.ekzweb.service.perms.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.perms.PermsRolePermission;
import com.ekz.ekzweb.mapper.perms.RolePermissionMapper;
import com.ekz.ekzweb.service.perms.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, PermsRolePermission> implements IRolePermissionService {
    @Autowired
    private RolePermissionMapper mapper;

    public List<String> getPermissionsByRoles (List<String> roles){
        LambdaQueryWrapper<PermsRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PermsRolePermission::getRole, roles);
        List<PermsRolePermission> permissions = mapper.selectList(queryWrapper);

        return permissions.stream()
                .map(PermsRolePermission::getPermission)
                .toList();
    }
}
