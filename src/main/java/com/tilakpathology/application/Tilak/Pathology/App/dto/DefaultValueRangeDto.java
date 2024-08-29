package com.tilakpathology.application.Tilak.Pathology.App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefaultValueRangeDto {

    private String testId;

    private Integer minAge;

    private Integer maxAge;

    private String defaultValue;

    private String defaultRemark;
}
