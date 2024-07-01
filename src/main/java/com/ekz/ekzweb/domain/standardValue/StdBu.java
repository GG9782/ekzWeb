package com.ekz.ekzweb.domain.standardValue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StdBu {
    @TableId(type = IdType.INPUT)
    private String bu;
    private String creator;
    private LocalDateTime createTime;
}
