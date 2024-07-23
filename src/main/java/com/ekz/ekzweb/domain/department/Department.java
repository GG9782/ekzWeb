package com.ekz.ekzweb.domain.department;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(autoResultMap = true)
public class Department {

    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    private String parentId;
    private String leader;
}
