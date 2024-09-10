package com.ekz.ekzweb.service.project.approvalPart;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.project.approvalPart.ApprovalPart;

import java.util.List;
import java.util.Map;

public interface IApprovalPartService extends IService<ApprovalPart> {
    List<Map<String, Object>> getFaiChartByPrjCode(String prjCode);

    List<Map<String, Object>> getCpkChartByPrjCode(String prjCode);
}
