package com.ekz.ekzweb.service.kpi.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.kpi.MpIssue;
import com.ekz.ekzweb.mapper.kpi.MpIssueMapper;
import com.ekz.ekzweb.service.kpi.IMpIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MpIssueServiceImpl extends ServiceImpl<MpIssueMapper, MpIssue> implements IMpIssueService {
    @Autowired
    private MpIssueMapper mapper;
}
