package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.standardValue.Bu;
import com.ekz.ekzweb.mapper.standardValue.BuMapper;
import com.ekz.ekzweb.service.standardValue.IBuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuServiceImpl extends ServiceImpl<BuMapper,Bu> implements IBuService {

    @Autowired
    private BuMapper buMapper;

}
