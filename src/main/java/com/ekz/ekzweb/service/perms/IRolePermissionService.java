package com.ekz.ekzweb.service.perms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.perms.PermsRolePermission;

import java.util.List;


public interface IRolePermissionService extends IService<PermsRolePermission> {
    List<String> getPermissionsByRoles (List<String> roles);

}
