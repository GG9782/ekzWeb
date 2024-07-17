package com.ekz.ekzweb.service.perms.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.perms.PermsUserProjectPermission;
import com.ekz.ekzweb.domain.perms.PermsUserRole;
import com.ekz.ekzweb.mapper.perms.UserProjectPermissionMapper;
import com.ekz.ekzweb.service.perms.IUserProjectPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProjectPermissionServiceImpl extends ServiceImpl<UserProjectPermissionMapper, PermsUserProjectPermission> implements IUserProjectPermissionService {
    @Autowired
    private UserProjectPermissionMapper mapper;

    public List<String> getPrincipalPermissions(String principal){
        return listObjs( new LambdaQueryWrapper<PermsUserProjectPermission>().select(PermsUserProjectPermission::getPermission).eq(PermsUserProjectPermission::getUser, principal) );
    }

    public ResponseEntity<String> saveList (List<String> users,String prjCode,String permission){
        List<PermsUserProjectPermission> userPrjPermsList = new ArrayList<>();
        for (String user : users) {
            PermsUserProjectPermission userPrjPerms = new PermsUserProjectPermission();
            userPrjPerms.setUser(user);
            userPrjPerms.setPrjCode(prjCode);
            userPrjPerms.setPermission(prjCode + ":" + permission);
        }
        saveBatch(userPrjPermsList);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    public ResponseEntity<String> saveOne (String user,String prjCode,String permission){
        PermsUserProjectPermission userPrjPerms = new PermsUserProjectPermission();
        userPrjPerms.setUser(user);
        userPrjPerms.setPrjCode(prjCode);
        userPrjPerms.setPermission(prjCode + ":" + permission);
        save(userPrjPerms);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
