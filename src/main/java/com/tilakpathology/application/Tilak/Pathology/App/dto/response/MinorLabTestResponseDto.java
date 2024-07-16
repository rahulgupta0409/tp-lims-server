package com.tilakpathology.application.Tilak.Pathology.App.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinorLabTestResponseDto {

    private String testId;

    private String testName;

    private Float testPrice;

    private String remarks;
}
