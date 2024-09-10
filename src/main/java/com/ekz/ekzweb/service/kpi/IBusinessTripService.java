package com.ekz.ekzweb.service.kpi;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.kpi.BusinessTrip;

import java.util.List;
import java.util.Map;

public interface IBusinessTripService extends IService<BusinessTrip> {
    List<Map<String, Object>> getSumGroupByMonthAndCustomer(Integer itemYear);
}
