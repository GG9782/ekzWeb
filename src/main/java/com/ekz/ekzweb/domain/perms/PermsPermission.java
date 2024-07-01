package com.ekz.ekzweb.domain.perms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PermsPermission {
    @TableId(type = IdType.INPUT)
    String permission;
    String description;
}
