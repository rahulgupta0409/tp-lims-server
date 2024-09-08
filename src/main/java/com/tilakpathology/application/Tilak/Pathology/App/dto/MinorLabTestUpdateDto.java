package com.tilakpathology.application.Tilak.Pathology.App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinorLabTestUpdateDto {

    private String testName;

    private Float testPrice;

    private String minorTestUnit;

    private String remarks;

    private String updatedBy;
}
