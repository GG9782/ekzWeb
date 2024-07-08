package com.ekz.ekzweb.controller.standardValue;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.standardValue.StdStage;
import com.ekz.ekzweb.service.project.prj.IAttributeService;
import com.ekz.ekzweb.service.standardValue.IStageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "StandardValue StdStage 接口")
@RestController
@RequestMapping("/StdStage")
public class StageController {

    @Autowired
    private IStageService service;

    @Autowired
    private IAttributeService attributeService;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<List<String>> getAllList() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        List<Object> resultList = service.listObjs(new LambdaQueryWrapper<StdStage>().select(StdStage::getStage));
        List<List<String>> result = new ArrayList<>();

        for (Object obj : resultList) {
            String json = (String) obj;
            JSONArray jsonArray = JSONUtil.parseArray(json);
            List<String> stageList = jsonArray.toList(String.class);
            result.add(stageList);
        }

        return result;
    }

    /** 全查 Object*/
    @Operation(summary = "全查 Object")
    @GetMapping("/allObject")
    public List<StdStage> getAllObject() {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");
        return service.lambdaQuery().orderByAsc(StdStage::getCustomer).list();
    }

    /** 依prjCode 查 可用Stage*/
    @Operation(summary = "依prjCode 查 可用Stage")
    @GetMapping("/getByPrjCode/{prjCode}")
    public List<StdStage> getByPrjCode(@PathVariable("prjCode") String prjCode) {
        // .checkRole("member");
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("member");

        AttributePO attributePO = attributeService.getById(prjCode);
        return service.lambdaQuery()
                .eq(StdStage::getCustomer,attributePO.getCustomer())
                .list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable("id") long id){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{customer}")
    public ResponseEntity<String> save(@PathVariable("customer") String customer,@RequestBody List<String> stage){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");


        StdStage dto = new StdStage();
        dto.setStage(stage);
        dto.setCustomer(customer);

        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);


        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个（根据id）")
    @PutMapping
    public ResponseEntity<String> update(@RequestBody StdStage dto){
        // checkPermission(projectManager"))
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("projectManager");

        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);

        dto.setCreateTime(LocalDateTime.now());
        service.updateById(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
