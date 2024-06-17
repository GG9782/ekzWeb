package com.ekz.ekzweb.service.developer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.developer.po.DeveloperRecord;
import com.ekz.ekzweb.mapper.developer.DeveloperRecordMapper;

import com.ekz.ekzweb.service.developer.IDeveloperRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperRecordServiceImp  extends ServiceImpl<DeveloperRecordMapper, DeveloperRecord> implements IDeveloperRecordService {
    @Autowired
    private DeveloperRecordMapper developerRecordMapper;
}
