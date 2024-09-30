package com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tests {

    private String testId;

    private String testName;

    private String value;

    private String remarks;

    private String unit;

    private Boolean isMajorLabTest = false;

    private List<MinorTest> minorLabTestList;

}
