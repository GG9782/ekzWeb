package com.ekz.ekzweb.mapper.standardValue;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ekz.ekzweb.domain.standardValue.StdCustomer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<StdCustomer> {
}
