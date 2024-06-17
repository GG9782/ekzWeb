package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.standardValue.Vendor;
import com.ekz.ekzweb.mapper.standardValue.VendorMapper;
import com.ekz.ekzweb.service.standardValue.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl extends ServiceImpl<VendorMapper, Vendor> implements IVendorService {

    @Autowired
    private VendorMapper vendorMapper;

}
