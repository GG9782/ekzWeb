package com.ekz.ekzweb.controller.project.TextIssue;

import com.ekz.ekzweb.domain.project.prj.jsonType.IssueChartJsonType;
import com.ekz.ekzweb.domain.project.prj.po.TStagePO;
import com.ekz.ekzweb.domain.project.prj.vo.IssueChartVO;
import com.ekz.ekzweb.domain.project.textIssue.TextIssue;
import com.ekz.ekzweb.service.project.prj.ITStageService;
import com.ekz.ekzweb.service.project.textIssue.ITextIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "Project IssueChart 接口")
@RestController
@RequestMapping("/prj/issueChart")
public class IssueChartController {
    @Autowired
    private ITextIssueService service;

    @Autowired
    private ITStageService tStageService;

    /** 查 issue barchart*/
    @Operation(summary = "依 prjCode 查 barchart")
    @GetMapping("/{prjCode}")
    public IssueChartVO getChartByPrjCode(@PathVariable("prjCode") String prjCode) {

        TStagePO tStagePO = tStageService.getById(prjCode);

        int tStageQuantity = tStagePO.getTStageQuantity();
        List<String> tStages = new ArrayList<>();
        for (int i = 1; i <= tStageQuantity; i++) { tStages.add("T" + i); }

        int dStageQuantity = tStagePO.getDStageQuantity();
        List<String> dStages = new ArrayList<>();
        for (int i = 1; i <= tStageQuantity; i++) { dStages.add("D" + i); }

        List<String> stages = new ArrayList<>();
        stages.addAll(dStages);
        stages.addAll(tStages);

        List<IssueChartJsonType> issueChartList = new ArrayList<>();

        for (String stage : stages){
            for (int severity :  Arrays.asList(1, 2, 3)){
                long closedQuantity = service.lambdaQuery()
                        .eq(TextIssue::getPrjCode,prjCode)
                        .eq(TextIssue::getStage,stage)
                        .eq(TextIssue::getSeverity,severity)
                        .eq(TextIssue::getStatus,"Closed")
                        .count();

                long openQuantity = service.lambdaQuery()
                        .eq(TextIssue::getPrjCode,prjCode)
                        .eq(TextIssue::getStage,stage)
                        .eq(TextIssue::getSeverity,severity)
                        .eq(TextIssue::getStatus,"Open")
                        .count();

                double passRate = Math.round((double) closedQuantity / (openQuantity + closedQuantity) * 10000.0) / 10000.0;

                IssueChartJsonType issueChartJsonType = new IssueChartJsonType();
                issueChartJsonType.setStage(stage);
                issueChartJsonType.setSeverity(severity);
                issueChartJsonType.setOpenQuantity((int) openQuantity);
                issueChartJsonType.setCloseQuantity((int) closedQuantity);
                issueChartJsonType.setPassRate(passRate);
                issueChartList.add(issueChartJsonType);
            }
        }
        IssueChartVO issueChartVO = new IssueChartVO();
        issueChartVO.setPrjCode(prjCode);
        issueChartVO.setIssueChart(issueChartList);
        return issueChartVO;
    }




}
