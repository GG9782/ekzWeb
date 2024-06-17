package com.ekz.ekzweb.service.standardValue.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.standardValue.Stage;
import com.ekz.ekzweb.mapper.standardValue.StageMapper;
import com.ekz.ekzweb.service.standardValue.IStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl extends ServiceImpl<StageMapper, Stage> implements IStageService {


    @Autowired
    private StageMapper stageMapper;

}
