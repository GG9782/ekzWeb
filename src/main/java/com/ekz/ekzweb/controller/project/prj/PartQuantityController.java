package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.dto.PartQuantityDTO;
import com.ekz.ekzweb.domain.project.prj.po.PartQuantityPO;
import com.ekz.ekzweb.domain.project.prj.vo.PartQuantityVO;
import com.ekz.ekzweb.service.project.prj.IPartQuantityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Project partQuantity接口")
@RestController
@RequestMapping("/prj/partQuantity")
public class PartQuantityController {

    @Autowired
    private IPartQuantityService partQuantityService;

/** Project PartQuantity*/
    /** 01.07.1 查 单个 Project PartQuantity*/
    @Operation(summary = "01.07.1 查 单个 PartQuantity")
    @GetMapping("/{prjCode}")
    public PartQuantityVO partQuantityById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( partQuantityService.getById(prjCode) ,PartQuantityVO.class);
    }

    /** 01.07.2 改 单个 Project PartQuantity*/
    @Operation(summary = "01.07.2 改 单个 PartQuantity")
    @PutMapping
    public ResponseEntity<String> updatePartQuantity(@RequestBody PartQuantityDTO dto){
        //  把DTO拷贝到PO
        PartQuantityPO po = BeanUtil.copyProperties(dto,PartQuantityPO.class);
        Subject subject = SecurityUtils.getSubject();
        po.setPartQuantityUpdater(subject.getPrincipals().toString());
        po.setPartQuantityUpdateTime(LocalDateTime.now());
        // 新增
        partQuantityService.updateById(po);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}

