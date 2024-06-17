package com.ekz.ekzweb.controller.project.prj;

import cn.hutool.core.bean.BeanUtil;
import com.ekz.ekzweb.domain.project.prj.vo.AttributeVO;
import com.ekz.ekzweb.service.project.prj.IAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Project attribute接口")
@RestController
@RequestMapping("/prj/attribute")
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;


/** Project Attribute*/

    /** 01.03.1 查 单个 Attribute*/
    @Operation(summary = "01.03.1 查 单个 Attribute")
    @GetMapping("/{prjCode}")
    public AttributeVO getAttributeById(@PathVariable("prjCode") String prjCode) {
        return BeanUtil.copyProperties( attributeService.getById(prjCode) ,AttributeVO.class);
    }

}

