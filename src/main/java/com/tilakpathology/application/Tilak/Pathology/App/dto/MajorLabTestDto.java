package com.tilakpathology.application.Tilak.Pathology.App.dto;

import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MajorLabTestDto {

    private String majorTestName;

    private List<MinorLabTest> minorLabTestList;

    private Float majorTestPrice;

    private String majorTestRemarks;

    private String createdBy;
}
