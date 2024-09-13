package com.ekz.ekzweb.service.kpi;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.kpi.Tss;

import java.util.List;
import java.util.Map;

public interface ITssService extends IService<Tss> {
    List<Map<String, Object>> getByGroupingRule1(String itemYearMonth);

    List<Map<String, Object>> getOTByDepartmentAndName(String itemYearMonth);

    List<Map<String, Object>> getByDepartmentAndCustomer(String itemYearMonth);

    List<Map<String, Object>> getByDepartmentProjectAdditionalRules(String itemYearMonth);

    List<Map<String, Object>> getByDepartment(String itemYearMonth);
}
