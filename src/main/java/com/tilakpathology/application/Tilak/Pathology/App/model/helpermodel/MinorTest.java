package com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinorTest {

    private String testId;

    private String testName;

    private String value;

    private String testUnit;

    private String remarks;

    private String defaultValue;
}
