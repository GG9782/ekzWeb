package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.standardValue.StdBusinessModel;
import com.ekz.ekzweb.mapper.standardValue.BusinessModelMapper;
import com.ekz.ekzweb.service.standardValue.IBusinessModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessModelServiceImpl extends ServiceImpl<BusinessModelMapper, StdBusinessModel> implements IBusinessModelService {

    @Autowired
    private BusinessModelMapper businessModelMapper;

}
