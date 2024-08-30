package com.ekz.ekzweb.domain.project.approvalPart;

import com.ekz.ekzweb.domain.jsonType.PassRateJsonType;
import lombok.Data;

import java.util.List;

@Data
public class ApprovalPartVO {

    private String prjCode;
    private List<PassRateJsonType> fai;
    private List<PassRateJsonType> cpk;
}
