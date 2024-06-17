package com.ekz.ekzweb.controller.standardValue;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.standardValue.Stage;
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

@Tag(name = "StandardValue Stage 接口")
@RestController
@RequestMapping("/Stage")
public class StageController {

    @Autowired
    private IStageService service;

    @Autowired
    private IAttributeService attributeService;

    /** 全查 List*/
    @Operation(summary = "全查 List")
    @GetMapping("/allList")
    public List<List<String>> getAllList() {
//        return service.listObjs(new LambdaQueryWrapper<Stage>().select(Stage::getStage));

        List<Object> resultList = service.listObjs(new LambdaQueryWrapper<Stage>().select(Stage::getStage));
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
    public List<Stage> getAllObject() {
        return service.lambdaQuery().orderByAsc(Stage::getCustomer).list();
    }

    /** 依prjCode 查 可用Stage*/
    @Operation(summary = "依prjCode 查 可用Stage")
    @GetMapping("/getByPrjCode/{prjCode}")
    public List<Stage> getByPrjCode(@PathVariable("prjCode") String prjCode) {
        AttributePO attributePO = attributeService.getById(prjCode);
        return service.lambdaQuery()
                .eq(Stage::getCustomer,attributePO.getCustomer())
                .list();
    }

    /** 删 单个*/
    @Operation(summary = "删 单个")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable("id") long id){
        service.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 增 单个 */
    @Operation(summary = "增 单个")
    @PostMapping("/{customer}")
    public ResponseEntity<String> save(@PathVariable("customer") String customer,@RequestBody List<String> stage){

        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Stage dto = new Stage();
        dto.setStage(stage);
        dto.setCustomer(customer);

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);


        dto.setCreateTime(LocalDateTime.now());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 改 单个*/
    @Operation(summary = "改 单个（根据id）")
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Stage dto){
        try {
            Subject subject = SecurityUtils.getSubject();
            String principals = subject.getPrincipals().toString();
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }

        Subject subject = SecurityUtils.getSubject();
        String principals = subject.getPrincipals().toString();
        dto.setCreator(principals);

        dto.setCreateTime(LocalDateTime.now());
        service.updateById(dto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
