package com.ekz.ekzweb.service.perms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.perms.PermsUserRole;

import java.util.List;

public interface IUserRoleService extends IService<PermsUserRole> {
    List<String> getPrincipalRoles(String principal);
}
