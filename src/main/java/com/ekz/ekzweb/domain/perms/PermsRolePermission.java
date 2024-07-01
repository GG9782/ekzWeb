package com.ekz.ekzweb.domain.perms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PermsRolePermission {
    @TableId(type = IdType.AUTO)
    @Schema(description = "自增id，新增时不要有此参数")
    Integer id;
    String role;
    String permission;
}
