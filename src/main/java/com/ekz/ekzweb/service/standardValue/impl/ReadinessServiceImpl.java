package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.standardValue.StdReadiness;
import com.ekz.ekzweb.mapper.standardValue.ReadinessMapper;
import com.ekz.ekzweb.service.standardValue.IReadinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadinessServiceImpl extends ServiceImpl<ReadinessMapper, StdReadiness> implements IReadinessService {

    @Autowired
    private ReadinessMapper readinessMapper;

    @Override
    public List<StdReadiness> getByProject(String prjCode, AttributePO attributePO) {
        LambdaQueryWrapper<StdReadiness> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(StdReadiness::getBu, attributePO.getBu())
                .and(i -> i.eq(StdReadiness::getCustomer, attributePO.getCustomer())
                        .or()
                        .isNull(StdReadiness::getCustomer))
                .and(j -> j.eq(StdReadiness::getProductType, attributePO.getProductType())
                        .or()
                        .isNull(StdReadiness::getProductType))
                .orderByAsc(StdReadiness::getItem);
        List<StdReadiness> list = readinessMapper.selectList(lambdaQueryWrapper);
        return list;
    }
}
