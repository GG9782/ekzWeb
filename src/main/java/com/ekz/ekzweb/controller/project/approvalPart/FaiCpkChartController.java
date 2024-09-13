package com.ekz.ekzweb.controller.project.approvalPart;

import com.ekz.ekzweb.domain.project.approvalPart.FaiGroupingRule2VO;
import com.ekz.ekzweb.service.project.approvalPart.IApprovalPartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Project faiCpkChart 接口")
@RestController
@RequestMapping("/prj/faiCpkChart")
public class FaiCpkChartController {
    @Autowired
    private IApprovalPartService service;
//    @Autowired
//    private ITStageService tStageService;

    @Operation(summary = "依 prjCode 查fai")
    @GetMapping("/fai/{prjCode}")
    public List<Map<String,Object>> getFaiChartByPrjCode(@PathVariable String prjCode) {
        return service.getFaiChartByPrjCode(prjCode);
    }

    @Operation(summary = "依 prjCode 查cpk")
    @GetMapping("/cpk/{prjCode}")
    public List<Map<String,Object>> getCpkChartByPrjCode(@PathVariable String prjCode) {
        return service.getCpkChartByPrjCode(prjCode);
    }
    @Operation(summary = "FaiGroupingRule1",description = "以customer、时间范围、partType 为筛选条件，以Vendor、tooling Stage为分组依据，对accept、alert、reject尺寸数量求和" )
    @GetMapping("/fai/getByGroupingRule1")
    public List<Map<String,Object>> getFaiByGroupingRule1(String customer, String partType, LocalDate startDate,LocalDate endDate) {
        return service.getFaiByGroupingRule1(customer,partType,startDate,endDate);
    }

    @Operation(summary = "FaiGroupingRule2",description = "以 year customer projectCode vendor type toolingStage 为分组依据，对accept、alert、reject尺寸数量求和。对查询结果重新组织，使其以item_year, customer, prj_code, vendor, part_type, tooling_stage,bu作为分组的依据，并且在每个分组中包含不同tooling_Stage的sumFaiAccept、 sumFaiAlert、sumFaiReject" )
    @GetMapping("/fai/getByGroupingRule2")
    public List<FaiGroupingRule2VO> getFaiByGroupingRule2() {
//        return service.getFaiByGroupingRule2();
        List<Map<String, Object>> originalResults = service.getFaiByGroupingRule2();
        List<FaiGroupingRule2VO> groupedResults = originalResults.stream().map(result -> {
            FaiGroupingRule2VO groupedData = new FaiGroupingRule2VO();
            groupedData.setItemYear((Integer) result.get("item_year"));
            groupedData.setCustomer((String) result.get("customer"));
            groupedData.setPrjCode((String) result.get("prj_code"));
            groupedData.setVendor((String) result.get("vendor"));
            groupedData.setPartType((String) result.get("part_type"));
            groupedData.setToolingStage((Integer) result.get("tooling_stage"));
            groupedData.setBu((String) result.get("bu"));
            // 设置不同tooling_Stage的sumFaiAccept、sumFaiAlert、sumFaiReject
            // 这里需要根据tooling_Stage的值将对应的统计数据放入不同的字段中
            int toolingStage = (Integer) result.get("tooling_stage");
            switch (toolingStage) {
                case 1 -> {
                    groupedData.setT1SumFaiAccept(((BigDecimal) result.get("sumFaiAccept")).intValue()); // 使用BigDecimal的intValue方法获取整数值
                    groupedData.setT1SumFaiAlert(((BigDecimal) result.get("sumFaiAlert")).intValue());
                    groupedData.setT1SumFaiReject(((BigDecimal) result.get("sumFaiReject")).intValue());
                }
                case 2 -> {
                    groupedData.setT2SumFaiAccept(((BigDecimal) result.get("sumFaiAccept")).intValue());
                    groupedData.setT2SumFaiAlert(((BigDecimal) result.get("sumFaiAlert")).intValue());
                    groupedData.setT2SumFaiReject(((BigDecimal) result.get("sumFaiReject")).intValue());
                }
                case 3 -> {
                    groupedData.setT3SumFaiAccept(((BigDecimal) result.get("sumFaiAccept")).intValue());
                    groupedData.setT3SumFaiAlert(((BigDecimal) result.get("sumFaiAlert")).intValue());
                    groupedData.setT3SumFaiReject(((BigDecimal) result.get("sumFaiReject")).intValue());
                }
                case 4 -> {
                    groupedData.setT4SumFaiAccept(((BigDecimal) result.get("sumFaiAccept")).intValue());
                    groupedData.setT4SumFaiAlert(((BigDecimal) result.get("sumFaiAlert")).intValue());
                    groupedData.setT4SumFaiReject(((BigDecimal) result.get("sumFaiReject")).intValue());
                }
                default -> {
                    // 处理默认情况
                }
            }
            return groupedData;
        }).collect(Collectors.toList());
        return groupedResults;
    }


}
