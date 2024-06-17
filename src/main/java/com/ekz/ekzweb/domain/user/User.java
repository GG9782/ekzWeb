package com.ekz.ekzweb.domain.user;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
//    持久化对象（Persistent Object，PO）：
//    和数据库形成映射关系。
//    简单说PO就是每一个数据库中的数据表，一个字段对应PO中的一个变量。（也就是我们常用的Entities）

@Data
@TableName(value = "user", autoResultMap = true)
public class User {

    @TableId(type = IdType.INPUT)
    private String id;

    // @TableField("`username`")
    private String name;

    // @TableField(exist = false)
    private String pwd;

    private Integer rid;

    private LocalDateTime time;

    private LocalDate date;
}
