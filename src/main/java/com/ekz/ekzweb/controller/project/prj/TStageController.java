package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.TStageDTO;
import com.ekz.ekzweb.domain.project.prj.po.TStagePO;
import com.ekz.ekzweb.domain.project.prj.vo.TStageVO;
import com.ekz.ekzweb.service.project.prj.ITStageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Project tStage接口")
@RestController
@RequestMapping("/prj/tStage")
public class TStageController {

    @Autowired
    private ITStageService tStageService;

/** Project TStage*/
    /** 01.08.1 查 单个 TStage */
    @Operation(summary = "01.08.1 查 单个 TStage")
    @GetMapping("/{prjCode}")
    public TStageVO tStageById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( tStageService.getById(prjCode) ,TStageVO.class);
    }

    /** 01.08.2 改 单个 TStage*/
    @Operation(summary = "01.08.2 改 单个 TStage")
    @PutMapping
    public ResponseEntity<String> updateTStage(@RequestBody TStageDTO dto){
        //  把DTO拷贝到PO
        TStagePO po = BeanUtil.copyProperties(dto,TStagePO.class);
        try {
            Subject subject = SecurityUtils.getSubject();
            po.setTStageUpdater(subject.getPrincipals().toString());
        } catch (Exception e) {
            // 在这里处理异常
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未认证");
        }
        po.setTStageUpdateTime(LocalDateTime.now());
        // 新增
        tStageService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    /** 01.08.1 查 单个 isTFinal */
    @Operation(summary = "查 单个 isTFinal")
    @GetMapping("/isTFinal/{prjCode}")
    public Boolean isTFinalById(@PathVariable("prjCode") String prjCode) {
        TStagePO po = tStageService.lambdaQuery()
                .select(TStagePO::getIsTFinal)
                .eq(TStagePO::getPrjCode,prjCode)
                .one();
        return po.getIsTFinal();
    }
}

