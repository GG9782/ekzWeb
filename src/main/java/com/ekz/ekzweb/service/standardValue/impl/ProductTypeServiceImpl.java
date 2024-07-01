package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.standardValue.StdProductType;
import com.ekz.ekzweb.mapper.standardValue.ProductTypeMapper;
import com.ekz.ekzweb.service.standardValue.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, StdProductType> implements IProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

}
