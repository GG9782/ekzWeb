package com.ekz.ekzweb.domain.standardValue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vendor {
    @TableId(type = IdType.INPUT)
    private String vendor;
    private String creator;
    private LocalDateTime createTime;
}
