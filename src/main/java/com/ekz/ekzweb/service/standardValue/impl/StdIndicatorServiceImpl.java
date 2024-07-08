package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.standardValue.StdIndicator;
import com.ekz.ekzweb.mapper.standardValue.StdIndicatorMapper;
import com.ekz.ekzweb.service.standardValue.IStdIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StdIndicatorServiceImpl extends ServiceImpl<StdIndicatorMapper, StdIndicator> implements IStdIndicatorService {

    @Autowired
    private StdIndicatorMapper stdIndicatorMapper;

}
