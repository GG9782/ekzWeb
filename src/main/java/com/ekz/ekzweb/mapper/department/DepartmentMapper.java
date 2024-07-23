package com.ekz.ekzweb.mapper.department;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ekz.ekzweb.domain.department.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper  extends BaseMapper<Department> {
}
