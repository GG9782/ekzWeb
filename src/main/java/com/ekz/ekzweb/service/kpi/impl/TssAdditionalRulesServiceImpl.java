package com.ekz.ekzweb.service.kpi.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.kpi.TssAdditionalRules;
import com.ekz.ekzweb.mapper.kpi.TssAdditionalRulesMapper;
import com.ekz.ekzweb.mapper.kpi.TssMapper;
import com.ekz.ekzweb.service.kpi.ITssAdditionalRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TssAdditionalRulesServiceImpl  extends ServiceImpl<TssAdditionalRulesMapper, TssAdditionalRules> implements ITssAdditionalRulesService {
    @Autowired
    private TssMapper mapper;
}
