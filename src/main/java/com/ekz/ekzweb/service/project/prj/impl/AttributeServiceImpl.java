package com.ekz.ekzweb.service.project.prj.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.mapper.project.prj.AttributeMapper;
import com.ekz.ekzweb.service.project.prj.IAttributeService;
import org.springframework.stereotype.Service;


@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, AttributePO> implements IAttributeService {

}
