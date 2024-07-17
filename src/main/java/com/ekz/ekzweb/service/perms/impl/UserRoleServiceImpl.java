package com.ekz.ekzweb.service.perms.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.perms.PermsUserRole;
import com.ekz.ekzweb.mapper.perms.UserRoleMapper;
import com.ekz.ekzweb.service.perms.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, PermsUserRole> implements IUserRoleService {
    @Autowired
    private UserRoleMapper mapper;

    public List<String> getPrincipalRoles(String principal){
        return listObjs( new LambdaQueryWrapper<PermsUserRole>().select(PermsUserRole::getRole).eq(PermsUserRole::getUser, principal) );
    }

}
