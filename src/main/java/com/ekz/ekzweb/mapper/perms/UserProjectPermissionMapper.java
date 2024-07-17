package com.ekz.ekzweb.mapper.perms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ekz.ekzweb.domain.perms.PermsUserProjectPermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProjectPermissionMapper extends BaseMapper<PermsUserProjectPermission> {
}
