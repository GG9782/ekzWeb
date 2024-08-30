package com.ekz.ekzweb.controller.project.approvalPart;

import com.ekz.ekzweb.domain.jsonType.PassRateJsonType;
import com.ekz.ekzweb.domain.project.approvalPart.ApprovalPart;
import com.ekz.ekzweb.domain.project.prj.po.TStagePO;
import com.ekz.ekzweb.service.project.approvalPart.IApprovalPartService;
import com.ekz.ekzweb.service.project.prj.ITStageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Project ApprovalPart 图接口")
@RestController
@RequestMapping("/prj/faiCpkChart")
public class FaiCpkChartController {
    @Autowired
    private IApprovalPartService service;
    @Autowired
    private ITStageService tStageService;

    /** 依 prjCode 查*/
    @Operation(summary = "依  prjCode 查")
    @GetMapping("/{faiOrCpk}/{prjCode}")
    public List<PassRateJsonType> getChartByPrjCode(@PathVariable String faiOrCpk,@PathVariable String prjCode) {

        // 执行查询
        List<ApprovalPart> approvalParts = service.lambdaQuery()
                .select(ApprovalPart::getFaiTotal,ApprovalPart::getFaiReject,ApprovalPart::getCpkTotal,ApprovalPart::getCpkReject,ApprovalPart::getToolingStage)
                .eq(ApprovalPart::getPrjCode, prjCode)
                .list();

        // 查询tStageQuantity用于循环控制
        Integer tStageQuantity= tStageService.lambdaQuery().select(TStagePO::getTStageQuantity).eq(TStagePO::getPrjCode,prjCode).one().getTStageQuantity();

        // 创建返回实例
        List<PassRateJsonType> result = new ArrayList<>();

        // 初始化分组数据结构
        for (int i = 1; i <= tStageQuantity; i++) {
            PassRateJsonType passRateJsonType = new PassRateJsonType();
            passRateJsonType.setName("T" + i);
            passRateJsonType.setTotal(0);
            passRateJsonType.setPass(0);
            passRateJsonType.setFail(0);
            passRateJsonType.setPassRate(0.0);
            result.add(passRateJsonType);
        }
        // 遍历ApprovalPart 对象，根据其 tooling_stage 将 faiTotal (cpkTotal) 和 faiReject (cpkReject) 字段的值累加到相应的 PassRateJsonType 对象中。
        for (ApprovalPart part : approvalParts) {
            int toolingStage = part.getToolingStage();
            PassRateJsonType passRateJsonType = result.get(toolingStage - 1);
            if (faiOrCpk.equals("fai")) {
                passRateJsonType.setTotal(passRateJsonType.getTotal() + part.getFaiTotal());
                passRateJsonType.setFail(passRateJsonType.getFail() + part.getFaiReject());
            } else {
                passRateJsonType.setTotal(passRateJsonType.getTotal() + part.getCpkTotal());
                passRateJsonType.setFail(passRateJsonType.getFail() + part.getCpkReject());
            }
        }
        // 计算每个 PassRateJsonType 对象的Pass和 passRate
        for (PassRateJsonType passRateJsonType : result) {
            passRateJsonType.setPass(passRateJsonType.getTotal() - passRateJsonType.getFail());
            double passRate = (double) passRateJsonType.getPass() / passRateJsonType.getTotal();
            passRate = Math.round(passRate * 100.0) / 100.0; // 保留两位小数
            passRateJsonType.setPassRate(passRate);
        }

        return result;

    }

}
