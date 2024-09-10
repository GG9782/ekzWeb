package com.ekz.ekzweb.service.kpi.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.kpi.Tss;
import com.ekz.ekzweb.mapper.kpi.TssMapper;
import com.ekz.ekzweb.service.kpi.ITssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
public class TssServiceImpl extends ServiceImpl<TssMapper, Tss> implements ITssService {
    @Autowired
    private TssMapper mapper;

    @Override
    public List<Map<String, Object>> getByGroupingRule1(String itemYearMonth) {
        QueryWrapper<Tss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_year_month", itemYearMonth)
                .groupBy("grouping_rule1")
                .select("grouping_rule1", "SUM(working_hour) as sumValue");
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getOTByDepartmentAndName(String itemYearMonth) {
        QueryWrapper<Tss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_year_month", itemYearMonth)
                .like("tag","加班")
                .groupBy("department","name")
                .select("department","name", "SUM(working_hour) as sumValue");
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getByDepartmentAndCustomer(String itemYearMonth) {
        QueryWrapper<Tss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_year_month", itemYearMonth)
                .groupBy("department","customer")
                .select("department","customer", "SUM(working_hour) as sumValue");
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getByDepartmentProjectAdditionalRules(String itemYearMonth) {
        QueryWrapper<Tss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_year_month", itemYearMonth)
                .groupBy("department","additionalPrjName")
                .select("department","additionalPrjName", "SUM(working_hour) as sumValue");
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getByDepartment(String itemYearMonth) {
        QueryWrapper<Tss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_year_month", itemYearMonth)
                .groupBy("department")
                .select("department", "SUM(working_hour) as sumValue");
        return mapper.selectMaps(queryWrapper);
    }


}
