package com.ekz.ekzweb.service.project.prj.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.ApprovalPO;
import com.ekz.ekzweb.mapper.project.prj.ApprovalMapper;
import com.ekz.ekzweb.service.project.prj.IApprovalService;
import org.springframework.stereotype.Service;


@Service
public class ApprovalServiceImpl extends ServiceImpl<ApprovalMapper, ApprovalPO> implements IApprovalService {

}
