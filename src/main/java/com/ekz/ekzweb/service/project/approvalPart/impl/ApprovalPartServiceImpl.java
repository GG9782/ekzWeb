package com.ekz.ekzweb.service.project.approvalPart.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.approvalPart.ApprovalPart;
import com.ekz.ekzweb.mapper.project.approvalPart.ApprovalPartMapper;
import com.ekz.ekzweb.service.project.approvalPart.IApprovalPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApprovalPartServiceImpl extends ServiceImpl<ApprovalPartMapper, ApprovalPart> implements IApprovalPartService {
    @Autowired
    ApprovalPartMapper mapper;

    @Override
    public List<Map<String, Object>> getFaiChartByPrjCode(String prjCode) {
        QueryWrapper<ApprovalPart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("prj_code", prjCode)
                .groupBy("tooling_stage")
                .select("tooling_stage", "SUM(fai_accept) as sumFaiAccept", "SUM(fai_alert) as sumFaiAlert", "SUM(fai_Reject) as sumFaiReject");
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getCpkChartByPrjCode(String prjCode) {
        QueryWrapper<ApprovalPart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("prj_code", prjCode)
                .groupBy("tooling_stage")
                .select("tooling_stage", "SUM(cpk_accept) as sumCpkAccept", "SUM(cpk_Reject) as sumCpkReject");
        return mapper.selectMaps(queryWrapper);
    }
}
