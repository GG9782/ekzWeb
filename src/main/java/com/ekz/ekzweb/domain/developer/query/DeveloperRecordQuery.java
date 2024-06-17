package com.ekz.ekzweb.domain.developer.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DeveloperRecordQuery {

    private String layer;
    private String subLayer;
    private String description;
    @Schema(description = "'earliestFinishDate'and 'latestFinishDate' should be both summited or neither")
    private LocalDate earliestFinishDate;
    @Schema(description = "'earliestFinishDate'and 'latestFinishDate' should be both summited or neither")
    private LocalDate latestFinishDate;

}
