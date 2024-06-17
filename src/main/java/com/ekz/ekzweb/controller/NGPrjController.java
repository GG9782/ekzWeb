package com.ekz.ekzweb.controller;

//@Tag(name = "Project接口")
//@RestController
//@RequestMapping("/ngprj")
public class NGPrjController {
//
//    @Autowired
//    private IPrjService prjService;
//
//    @Autowired
//    private IApprovalService approvalService;
//
//    @Autowired
//    private IAttributeService attributeService;
//
//    @Autowired
//    private IIndicatorService indicatorService;
//
//    @Autowired
//    private IIssueService issueService;
//
//    @Autowired
//    private IMemberService memberService;
//
//    @Autowired
//    private IPartQuantityService partQuantityService;
//
//    @Autowired
//    private IScheduleService scheduleService;
//
//    @Autowired
//    private ITStageService tStageService;
//
//
////    @Operation(summary = "根据id修改member接口")
////    @PutMapping("/member")
////    public void updateUser(@RequestBody MemberDTO dto){
////        //  把DTO拷贝到PO
////        MemberPO po = BeanUtil.copyProperties(dto,MemberPO.class);
////        po.setMemberUpdateTime(LocalDateTime.now());
////        // 新增
////        memberService.updateById(po);
////    }
//
//    /** 01.01.1 全查 Overview*/
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
//    /** 01.02.1 增 单个 Project */
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
//        return BeanUtil.copyProperties( attributeService.getById(prjCode) ,AttributeVO.class);
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
//        return BeanUtil.copyProperties( indicatorService.getById(prjCode) ,IndicatorVO.class);
//    }
//
//    /** 01.04.2 改 单个 Project Indicator*/
//    @Operation(summary = "01.04.2 改 单个 Indicator")
//    @PutMapping("/indicator")
//    public ResponseEntity<String> updateIndicator( @RequestBody IndicatorDTO dto){
//        //  把DTO拷贝到PO
//        IndicatorPO po = BeanUtil.copyProperties(dto,IndicatorPO.class);
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            po.setIndicatorUpdater(subject.getPrincipals().toString());
//        } catch (Exception e) {
//            // 在这里处理异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
//        }
//        po.setIndicatorUpdateTime(LocalDateTime.now());
//        // 新增
//        indicatorService.updateById(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Member*/
//
//    /** 01.05.1 查 单个 Project Member*/
//    @Operation(summary = "01.05.1 查 单个 Member")
//    @GetMapping("/member/{prjCode}")
//    public MemberVO getMemberById(@PathVariable("prjCode") String prjCode) {
//        return BeanUtil.copyProperties( memberService.getById(prjCode) ,MemberVO.class);
//    }
//
//    /** 01.05.2 改 单个 Project Member*/
//    @Operation(summary = "01.05.2 改 单个 Member")
//    @PutMapping("/member")
//    public ResponseEntity<String> updateMember(@RequestBody MemberDTO dto){
//        //  把DTO拷贝到PO
//        MemberPO po = BeanUtil.copyProperties(dto,MemberPO.class);
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            po.setMemberUpdater(subject.getPrincipals().toString());
//        } catch (Exception e) {
//            // 在这里处理异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
//        }
//        po.setMemberUpdateTime(LocalDateTime.now());
//        // 新增
//        memberService.updateById(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Schedule*/
//
//    /** 01.06.1 查 单个 Project Schedule*/
//    @Operation(summary = "01.06.1 查 单个 Schedule")
//    @GetMapping("/schedule/{prjCode}")
//    public ScheduleVO getScheduleById(@PathVariable("prjCode") String prjCode) {
//        return BeanUtil.copyProperties( scheduleService.getById(prjCode) ,ScheduleVO.class);
//    }
//
//    /** 01.06.2改 单个 Project Schedule*/
//    @Operation(summary = "01.06.2 改 单个 Schedule")
//    @PutMapping("/schedule")
//    public ResponseEntity<String> updateSchedule(@RequestBody ScheduleDTO dto){
//        //  把DTO拷贝到PO
//        SchedulePO po = BeanUtil.copyProperties(dto,SchedulePO.class);
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            po.setScheduleUpdater(subject.getPrincipals().toString());
//        } catch (Exception e) {
//            // 在这里处理异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
//        }
//        po.setScheduleUpdateTime(LocalDateTime.now());
//        // 新增
//        scheduleService.updateById(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project PartQuantity*/
//    /** 01.07.1 查 单个 Project PartQuantity*/
//    @Operation(summary = "01.07.1 查 单个 PartQuantity")
//    @GetMapping("/partQuantity/{prjCode}")
//    public PartQuantityVO partQuantityById(@PathVariable("prjCode") String prjCode) {
//        return BeanUtil.copyProperties( partQuantityService.getById(prjCode) ,PartQuantityVO.class);
//    }
//
//    /** 01.07.2 改 单个 Project PartQuantity*/
//    @Operation(summary = "01.07.2 改 单个 PartQuantity")
//    @PutMapping("/partQuantity/{prjCode}")
//    public ResponseEntity<String> updatePartQuantity(@RequestBody PartQuantityDTO dto){
//        //  把DTO拷贝到PO
//        PartQuantityPO po = BeanUtil.copyProperties(dto,PartQuantityPO.class);
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            po.setPartQuantityUpdater(subject.getPrincipals().toString());
//        } catch (Exception e) {
//            // 在这里处理异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
//        }
//        po.setPartQuantityUpdateTime(LocalDateTime.now());
//        // 新增
//        partQuantityService.updateById(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project TStage*/
//    /** 01.08.1 查 单个 TStage */
//    @Operation(summary = "01.08.1 查 单个 TStage")
//    @GetMapping("/tStage/{prjCode}")
//    public TStageVO tStageById(@PathVariable("prjCode") String prjCode) {
//        return BeanUtil.copyProperties( tStageService.getById(prjCode) ,TStageVO.class);
//    }
//
//    /** 01.08.2 改 单个 TStage*/
//    @Operation(summary = "01.08.2 改 单个 TStage")
//    @PutMapping("/tStage/{prjCode}")
//    public ResponseEntity<String> updateTStage(@RequestBody TStageDTO dto){
//        //  把DTO拷贝到PO
//        TStagePO po = BeanUtil.copyProperties(dto,TStagePO.class);
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            po.setTStageUpdater(subject.getPrincipals().toString());
//        } catch (Exception e) {
//            // 在这里处理异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
//        }
//        po.setTStageUpdateTime(LocalDateTime.now());
//        // 新增
//        tStageService.updateById(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
//
///** Project Approval*/
//    /** 01.09.1 查 单个 Approval */
//    @Operation(summary = "01.09.1查 单个 Approval")
//    @GetMapping("/approval/{prjCode}")
//    public ApprovalVO approvalById(@PathVariable("prjCode") String prjCode) {
//        return BeanUtil.copyProperties( approvalService.getById(prjCode) ,ApprovalVO.class);
//    }
//
//    /** 01.09.2 改 单个 Approval*/
//    @Operation(summary = "01.09.2 改 单个 Approval")
//    @PutMapping("/approval/{prjCode}")
//    public ResponseEntity<String> updateApproval(@RequestBody ApprovalDTO dto){
//        //  把DTO拷贝到PO
//        ApprovalPO po = BeanUtil.copyProperties(dto,ApprovalPO.class);
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            po.setApprovalUpdater(subject.getPrincipals().toString());
//        } catch (Exception e) {
//            // 在这里处理异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
//        }
//        po.setApprovalUpdateTime(LocalDateTime.now());
//        // 新增
//        approvalService.updateById(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//
///** Project Issue */
//    /** 01.10.1 查 单个 Issue */
//    @Operation(summary = "01.10.1 查 单个 Issue")
//    @GetMapping("/issue/{prjCode}")
//    public IssueVO issueById(@PathVariable("prjCode") String prjCode) {
//        return BeanUtil.copyProperties( issueService.getById(prjCode) ,IssueVO.class);
//    }
//    /** 01.10.2 改 单个 Issue*/
//    @Operation(summary = "01.10.2 改 单个 Issue")
//    @PutMapping("/issue/{prjCode}")
//    public ResponseEntity<String> updateIssue(@RequestBody IssueDTO dto){
//        //  把DTO拷贝到PO
//        IssuePO po = BeanUtil.copyProperties(dto,IssuePO.class);
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            po.setIssueUpdater(subject.getPrincipals().toString());
//        } catch (Exception e) {
//            // 在这里处理异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
//        }
//        po.setIssueUpdateTime(LocalDateTime.now());
//        // 新增
//        issueService.updateById(po);
//        return ResponseEntity.status(HttpStatus.OK).body("OK");
//    }
//

}

