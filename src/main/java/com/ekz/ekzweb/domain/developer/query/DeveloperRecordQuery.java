package com.ekz.ekzweb.domain.developer.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DeveloperRecordQuery {

    private String layer;
    private String sublayer;
    private String description;
    @Schema(description = "earliestFinishDate和latestFinishDate要么都有，要么都没有，不能单有其中一个")
    private LocalDate earliestFinishDate;
    @Schema(description = "earliestCreateDate和latestCreateDate要么都有，要么都没有，不能单有其中一个")
    private LocalDate latestFinishDate;

}
