package com.ekz.ekzweb.service.project.prj.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.MemberPO;
import com.ekz.ekzweb.mapper.project.prj.MemberMapper;
import com.ekz.ekzweb.service.project.prj.IMemberService;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberPO> implements IMemberService {

}
