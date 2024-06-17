package com.ekz.ekzweb.service.project.prj.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.PartQuantityPO;
import com.ekz.ekzweb.mapper.project.prj.PartQuantityMapper;
import com.ekz.ekzweb.service.project.prj.IPartQuantityService;
import org.springframework.stereotype.Service;


@Service
public class PartQuantityServiceImpl extends ServiceImpl<PartQuantityMapper, PartQuantityPO> implements IPartQuantityService {

}
