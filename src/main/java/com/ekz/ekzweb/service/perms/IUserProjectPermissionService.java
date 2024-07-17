package com.ekz.ekzweb.service.perms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.perms.PermsUserProjectPermission;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserProjectPermissionService extends IService<PermsUserProjectPermission> {

    List<String> getPrincipalPermissions(String principal);

    ResponseEntity<String> saveList (List<String> users, String prjCode, String permission);

    ResponseEntity<String> saveOne (String user,String prjCode,String permission);
}
