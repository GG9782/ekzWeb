package com.ekz.ekzweb.domain.perms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PermsUserProjectPermission {
    @TableId(type = IdType.ASSIGN_UUID)
    @Schema(description = "UUID，新增时不要有此参数")
    String id;
    String user;
    String prjCode;
    String justPermission;
    @Schema(description = "虚拟列，新增和修改时不要有此参数")
    String permission;

}
