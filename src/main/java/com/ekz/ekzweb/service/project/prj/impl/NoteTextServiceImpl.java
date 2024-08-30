package com.ekz.ekzweb.service.project.prj.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.project.prj.po.NoteTextPO;
import com.ekz.ekzweb.mapper.project.prj.NoteTextMapper;
import com.ekz.ekzweb.service.project.prj.INoteTextService;
import org.springframework.stereotype.Service;

@Service
public class NoteTextServiceImpl extends ServiceImpl<NoteTextMapper, NoteTextPO> implements INoteTextService {
}
