package com.ekz.ekzweb.service.project.highLowLight.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.textHighLowLight.TextHighLowLight;
import com.ekz.ekzweb.mapper.project.hignLowLight.HighLowLightMapper;
import com.ekz.ekzweb.service.project.highLowLight.IHighLowLightService;
import org.springframework.stereotype.Service;


@Service
public class HighLowLightServiceImpl extends ServiceImpl<HighLowLightMapper, TextHighLowLight> implements IHighLowLightService {

}
