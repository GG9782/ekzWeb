package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.standardValue.StdCustomer;
import com.ekz.ekzweb.mapper.standardValue.CustomerMapper;
import com.ekz.ekzweb.service.standardValue.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, StdCustomer> implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;


}
