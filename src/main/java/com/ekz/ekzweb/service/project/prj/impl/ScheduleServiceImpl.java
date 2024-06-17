package com.ekz.ekzweb.service.project.prj.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.SchedulePO;
import com.ekz.ekzweb.mapper.project.prj.ScheduleMapper;
import com.ekz.ekzweb.service.project.prj.IScheduleService;
import org.springframework.stereotype.Service;


@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, SchedulePO> implements IScheduleService {

}
