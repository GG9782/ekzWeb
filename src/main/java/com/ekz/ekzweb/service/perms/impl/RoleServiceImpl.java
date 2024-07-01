package com.ekz.ekzweb.service.perms.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.perms.PermsRole;
import com.ekz.ekzweb.mapper.perms.RoleMapper;
import com.ekz.ekzweb.service.perms.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, PermsRole> implements IRoleService {
    @Autowired
    private RoleMapper mapper;
}
