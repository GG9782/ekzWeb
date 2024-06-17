package com.ekz.ekzweb.controller;


//@Tag(name = "Project接口")
//@RestController
//@RequestMapping("/oldprj")
public class oldPrjController {
//
//    @Autowired
//    private IPrjService prjService;
//
///** 01.01.1 全查 Overview*/
//    @Operation(summary = "01.01.1 全查 Overview")
//    @GetMapping("/overview")
//    public List<OverviewVO> queryOverview() {
//
//        List<Project> poList = prjService.queryOverview();
//        List<OverviewVO> voList = new ArrayList<>();
//
//        for (Project project : poList) {
//            OverviewVO overviewVO = BeanUtil.copyProperties(project,OverviewVO.class);
////            overviewVO.setCurrentStage(project.getSchedule());
//            voList.add(overviewVO);
//        }
//        return voList;
//    }
///** 01.02.1 增 单个 Project */
//    @Operation(summary = "01.02.1 增 单个 Project")
//    @PostMapping("/save")
//    public ResponseEntity<String> save(@RequestBody AttributeDTO dto){
//        prjService.save(
//                BeanUtil.copyProperties(dto,Project.class)
//        );
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Attribute*/
//
//    /** 01.03.1 查 单个 Attribute*/
//    @Operation(summary = "01.03.1 查 单个 Attribute")
//    @GetMapping("/attribute/{prjCode}")
//    public AttributeVO getAttributeById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getAttributeById(prjCode);
//        AttributeVO attributeVO = BeanUtil.copyProperties(project,AttributeVO.class);
//        return attributeVO;
//    }
//
//    /** 01.03.2 复杂条件 查 多个 Attribute*/
//    @Operation(summary = "01.03.2 复杂条件 查 多个 Attribute")
//    @GetMapping("/attribute/list")
//    public List<AttributeVO> queryPrjAttributeList(AttributeQuery query) {
//        System.out.println(query);
//        List<Project> attributePO = prjService.queryPrjAttributeList(
//                query.getPrjCode(),
//                query.getPrjName(),
//                query.getBu(),
//                query.getCustomer(),
//                query.getBusinessModel(),
//                query.getCoreInvest(),
//                query.getCoreInvest(),
//                query.getProductType(),
//                query.getCreator(),
//                query.getEarliestCreateDate(),
//                query.getLatestCreateDate()
//        );
//        // 2.把PO拷贝到VO
//        return BeanUtil.copyToList(attributePO, AttributeVO.class);
//    }
//
//    /** 01.03.3 改 单个 Project Attribute*/
//    @Operation(summary = "01.03.3 改 单个 Attribute")
//    @PutMapping("/attribute/{prjCode}")
//    public ResponseEntity<String> updateAttribute(@PathVariable("prjCode") String prjCode, @RequestBody AttributeDTO dto){
//        prjService.updateAttribute(prjCode, dto);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
//    /** 01.03.4 逻辑删除 单个 Project Attribute*/
//    @Operation(summary = "01.03.4 逻辑删除 单个 Attribute")
//    @DeleteMapping("/attribute/logicDelete/{prjCode}")
//    public ResponseEntity<String>  logicDeleteById(@PathVariable("prjCode") String prjCode){
//        prjService.removeById(prjCode);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
//    /** 01.03.5 逻辑删除撤销 单个 Project Attribute*/
//    @Operation(summary = " 01.03.5 逻辑删除撤销 单个 Attribute")
//    @DeleteMapping("/attribute/cancelLogicDelete/{prjCode}")
//    public ResponseEntity<String>  cancelLogicDeleteById(@PathVariable("prjCode") String prjCode){
//        prjService.cancelLogicDeleteById(prjCode);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
//    /** 01.03.6 物理删除 单个 Project Attribute*/
//    @Operation(summary = "01.03.6 物理删除 单个 Attribute")
//    @DeleteMapping("/attribute/physicsDelete/{prjCode}")
//    public ResponseEntity<String> physicsDeleteById(@PathVariable("prjCode") String prjCode){
//        prjService.physicsDeleteById(prjCode);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Indicator*/
//
//    /** 01.04.1 查 单个 Project Indicator*/
//    @Operation(summary = "01.04.1 查 单个 Indicator")
//    @GetMapping("/indicator/{prjCode}")
//    public IndicatorVO getIndicatorById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getIndicatorById(prjCode);
//        IndicatorVO indicatorVO = BeanUtil.copyProperties(project,IndicatorVO.class);
//        return indicatorVO;
//    }
//
//    /** 01.04.2 改 单个 Project Indicator*/
//    @Operation(summary = "01.04.2 改 单个 Indicator")
//    @PutMapping("/indicator")
//    public ResponseEntity<String> updateIndicator( @RequestBody IndicatorDTO dto){
//        System.out.println("dto");
//        System.out.println(dto.getIndicatorUserDefine());
//        Project po = BeanUtil.copyProperties(dto,Project.class);
//        System.out.println("po");
//        System.out.println(po.getIndicatorUserDefine());
//        prjService.updateIndicator(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Member*/
//
//    /** 01.05.1 查 单个 Project Member*/
//    @Operation(summary = "01.05.1 查 单个 Member")
//    @GetMapping("/member/{prjCode}")
//    public MemberVO getMemberById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getMemberById(prjCode);
//        MemberVO memberVO = BeanUtil.copyProperties(project,MemberVO.class);
//        return memberVO;
//    }
//
//    /** 01.05.2 改 单个 Project Member*/
//
//    @Operation(summary = "01.05.2 改 单个 Member")
//    @PutMapping("/member")
//    public ResponseEntity<String> updateMember(@RequestBody MemberDTO dto){
////        Project po = BeanUtil.copyProperties(dto,Project.class);
//        prjService.updateMember(dto);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Schedule*/
//
//    /** 01.06.1 查 单个 Project Schedule*/
//    @Operation(summary = "01.06.1 查 单个 Schedule")
//    @GetMapping("/schedule/{prjCode}")
//    public ScheduleVO getScheduleById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getScheduleById(prjCode);
//        ScheduleVO scheduleVO = BeanUtil.copyProperties(project,ScheduleVO.class);
//        return scheduleVO;
//    }
//
//    /** 01.06.2改 单个 Project Schedule*/
//    @Operation(summary = "01.06.2 改 单个 Schedule")
//    @PutMapping("/schedule/{prjCode}")
//    public ResponseEntity<String> updateSchedule(@PathVariable("prjCode") String prjCode, @RequestBody ScheduleDTO dto){
//        prjService.updateSchedule(prjCode, dto);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
//
///** Project PartQuantity*/
//    /** 01.07.1 查 单个 Project PartQuantity*/
//    @Operation(summary = "01.07.1 查 单个 PartQuantity")
//    @GetMapping("/partQuantity/{prjCode}")
//    public PartQuantityVO getPartQuantityById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getPartQuantityById(prjCode);
//        PartQuantityVO partQuantityVO = BeanUtil.copyProperties(project,PartQuantityVO.class);
//        return partQuantityVO;
//    }
//
//    /** 01.07.2 改 单个 Project PartQuantity*/
//    @Operation(summary = "01.07.2 改 单个 PartQuantity")
//    @PutMapping("/partQuantity/{prjCode}")
//    public ResponseEntity<String> updatePartQuantity(@PathVariable("prjCode") String prjCode, @RequestBody PartQuantityDTO dto){
//        prjService.updatePartQuantity(prjCode, dto);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project TStage*/
//    /** 01.08.1 查 单个 TStage */
//    @Operation(summary = "01.08.1 查 单个 TStage")
//    @GetMapping("/tStage/{prjCode}")
//    public TStageVO getTStageById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getTStageById(prjCode);
//        TStageVO tStageVO = BeanUtil.copyProperties(project, TStageVO.class);
//        return tStageVO;
//    }
//    /** 01.08.2 改 单个 TStage*/
//    @Operation(summary = "01.08.2 改 单个 TStage")
//    @PutMapping("/tStage/{prjCode}")
//    public ResponseEntity<String> updateTStage(@PathVariable("prjCode") String prjCode, @RequestBody List<String> dto){
//        prjService.updateTStage(prjCode, dto);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Approval*/
//    /** 01.09.1 查 单个 Approval */
//    @Operation(summary = "01.09.1查 单个 Approval")
//    @GetMapping("/approval/{prjCode}")
//    public ApprovalVO getApprovalById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getApprovalById(prjCode);
//        ApprovalVO approvalVO = BeanUtil.copyProperties(project, ApprovalVO.class);
//        return approvalVO;
//    }
//    /** 01.09.2 改 单个 Approval*/
//    @Operation(summary = "01.09.2 改 单个 Approval")
//    @PutMapping("/approval/{prjCode}")
//    public ResponseEntity<String> updateApproval(@PathVariable("prjCode") String prjCode, @RequestBody ApprovalDTO dto){
//        prjService.updateApproval(prjCode, dto);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Issue */
//    /** 01.10.1 查 单个 Issue */
//    @Operation(summary = "01.10.1 查 单个 Issue")
//    @GetMapping("/issue/{prjCode}")
//    public IssueVO getIssueById(@PathVariable("prjCode") String prjCode) {
//        Project project = prjService.getIssueById(prjCode);
//        IssueVO issueVO = BeanUtil.copyProperties(project, IssueVO.class);
//        return issueVO;
//    }
//    /** 01.10.2 改 单个 Issue*/
//    @Operation(summary = "01.10.2 改 单个 Issue")
//    @PutMapping("/issue/{prjCode}")
//    public ResponseEntity<String> updateIssue(@PathVariable("prjCode") String prjCode, @RequestBody IssueDTO dto){
//        prjService.updateIssue(prjCode, dto);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }

/** ProjectTextIssue*/
    /** 查 单个 ProjectTextIssue*/
    /** 增 单个 ProjectTextIssue*/
    /** 改 单个 ProjectTextIssue*/
    /** 删 单个 ProjectTextIssue*/

/** ProjectTextHL*/
    /** 查 单个 ProjectTextHL*/
    /** 增 单个 ProjectTextHL*/
    /** 改 单个 ProjectTextHL*/
    /** 删 单个 ProjectTextHL*/

/** ProjectTextReadiness*/
    /** 查 单个 ProjectTextReadiness*/
    /** 增 单个 ProjectTextReadiness*/
    /** 改 单个 ProjectTextReadiness*/
    /** 删 单个 ProjectTextReadiness*/
}
