package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.MajorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MajorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MinorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.MajorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;

import java.util.List;
import java.util.Optional;

public interface MajorLabTestService {

    MajorLabTest addMajorLabTest(MajorLabTestDto majorLabTestDto);

    List<MajorLabTest> getAllMajorLabTests();

    Optional<MajorLabTestResponseDto> getAllMajorLabTestByTestId(String majorLabTestId);

    MajorLabTest updateMajorLabTest(MajorLabTestDto majorLabTestDto, String majorLabTestId);


}
