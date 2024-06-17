package com.ekz.ekzweb.domain.project.prj.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemberVO {

    private String prjCode;
    private List<String> leader;
    private List<String> meMember;
    private List<String> idMember;
    private List<String> packingMember;
    private LocalDateTime memberUpdateTime;
    private String memberUpdater;
}
