package com.ekz.ekzweb.service.project.prj.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.IndicatorPO;
import com.ekz.ekzweb.mapper.project.prj.IndicatorMapper;
import com.ekz.ekzweb.service.project.prj.IIndicatorService;
import org.springframework.stereotype.Service;




@Service
public class IndicatorServiceImpl extends ServiceImpl<IndicatorMapper, IndicatorPO> implements IIndicatorService {

}
