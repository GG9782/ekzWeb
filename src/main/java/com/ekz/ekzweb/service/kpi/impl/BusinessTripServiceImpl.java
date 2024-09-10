package com.ekz.ekzweb.service.kpi.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.kpi.BusinessTrip;
import com.ekz.ekzweb.domain.kpi.Tss;
import com.ekz.ekzweb.mapper.kpi.BusinessTripMapper;
import com.ekz.ekzweb.service.kpi.IBusinessTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class BusinessTripServiceImpl extends ServiceImpl<BusinessTripMapper, BusinessTrip> implements IBusinessTripService {
    @Autowired
    private BusinessTripMapper mapper;

    @Override
    public List<Map<String, Object>> getSumGroupByMonthAndCustomer(Integer itemYear) {

        int thisYear = LocalDate.now().getYear();

        QueryWrapper<BusinessTrip> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("item_year",itemYear)
                .groupBy("customer","item_year","item_month")
                .select("customer","year_month", "SUM(days) as sumDays");

        return mapper.selectMaps(queryWrapper);
    }
}
