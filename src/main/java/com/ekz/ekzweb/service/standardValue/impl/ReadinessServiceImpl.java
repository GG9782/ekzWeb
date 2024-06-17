package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.standardValue.Readiness;
import com.ekz.ekzweb.mapper.standardValue.ReadinessMapper;
import com.ekz.ekzweb.service.standardValue.IReadinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadinessServiceImpl extends ServiceImpl<ReadinessMapper, Readiness> implements IReadinessService {

    @Autowired
    private ReadinessMapper readinessMapper;

    @Override
    public List<Readiness> getByProject(String prjCode, AttributePO attributePO) {
        LambdaQueryWrapper<Readiness> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(Readiness::getBu, attributePO.getBu())
                .and(i -> i.eq(Readiness::getCustomer, attributePO.getCustomer())
                        .or()
                        .isNull(Readiness::getCustomer))
                .and(j -> j.eq(Readiness::getProductType, attributePO.getProductType())
                        .or()
                        .isNull(Readiness::getProductType))
                .orderByAsc(Readiness::getItem);
        List<Readiness> list = readinessMapper.selectList(lambdaQueryWrapper);
        return list;
    }
}
