package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MinorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;

import java.util.List;
import java.util.Optional;

public interface MinorLabTestService {

    MinorLabTestResponseDto addMinorLabTest(MinorLabTestDto minorLabTestDto);

    List<MinorLabTest> getAllMinorLabTests();

    Optional<MinorLabTestResponseDto> getAllMinorLabTestByTestId(String testId);
}
