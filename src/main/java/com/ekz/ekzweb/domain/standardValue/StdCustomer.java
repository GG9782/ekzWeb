package com.ekz.ekzweb.domain.standardValue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StdCustomer {
    @TableId(type = IdType.INPUT)
    private String customer;
    private String bu;
    private String creator;
    private LocalDateTime createTime;
}
