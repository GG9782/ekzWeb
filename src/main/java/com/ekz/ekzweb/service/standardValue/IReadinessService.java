package com.ekz.ekzweb.service.standardValue;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.standardValue.Readiness;

import java.util.List;

public interface IReadinessService extends IService<Readiness> {
    List<Readiness> getByProject(String prjCode, AttributePO attributePO);
}
