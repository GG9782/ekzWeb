package com.ekz.ekzweb.service.project.prj.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.TStagePO;
import com.ekz.ekzweb.mapper.project.prj.TStageMapper;
import com.ekz.ekzweb.service.project.prj.ITStageService;
import org.springframework.stereotype.Service;


@Service
public class TStageServiceImpl extends ServiceImpl<TStageMapper, TStagePO> implements ITStageService {

}
