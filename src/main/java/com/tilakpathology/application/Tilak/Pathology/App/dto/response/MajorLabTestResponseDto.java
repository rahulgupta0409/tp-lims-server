package com.tilakpathology.application.Tilak.Pathology.App.dto.response;

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
public class MajorLabTestResponseDto {

    private String majorTestId;

    private String majorTestName;

    private List<MinorLabTest> minorLabTestList;

    private Float majorTestPrice;

    private String majorTestRemarks;
}
