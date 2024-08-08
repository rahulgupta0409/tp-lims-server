package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.MinorLabTestRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MinorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.ResourceNotFoundException;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.MinorLabTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;


@Service
public class MinorLabTestServiceImpl implements MinorLabTestService {

    private final Logger log = LoggerFactory.getLogger(MinorLabTestServiceImpl.class);

    @Autowired
    private MinorLabTestRepository minorLabTestRepository;


    @Override
    public MinorLabTestResponseDto addMinorLabTest(MinorLabTestDto minorLabTestDto) {
        MinorLabTest minorLabTest = MinorLabTest.builder()
                .testId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .testName(minorLabTestDto.getTestName())
                .testPrice(minorLabTestDto.getTestPrice())
                .remarks(minorLabTestDto.getRemarks())
                .build();
        minorLabTestRepository.save(minorLabTest);

        log.info("Minor Test Created Successfully with testId: {}", minorLabTest.getTestId() );

        return MinorLabTestResponseDto.builder()
                .testId(minorLabTest.getTestId())
                .testName(minorLabTest.getTestName())
                .testPrice(minorLabTest.getTestPrice())
                .build();
    }

    @Override
    public List<MinorLabTest> getAllMinorLabTests() {
        List<MinorLabTest> minorLabTest = minorLabTestRepository.findAll();
        log.info("Fetched All List of Minor Lab Tests");
        return minorLabTest;
    }

    @Override
    public Optional<MinorLabTestResponseDto> getAllMinorLabTestByTestId(String testId) {
        MinorLabTest minorLabTest = minorLabTestRepository.getTestByTestId(testId);
        if (minorLabTest != null){
            return Optional.ofNullable(MinorLabTestResponseDto.builder()
                    .testId(minorLabTest.getTestId())
                    .testName(minorLabTest.getTestName())
                    .testPrice(minorLabTest.getTestPrice())
                    .remarks(minorLabTest.getRemarks())
                    .build());
        }
        log.info("Test Not Found for TestId: {}", testId);
        throw new ResourceNotFoundException("Test Not Found");
    }

    @Override
    public MinorLabTest updateMinorLabTest(MinorLabTestDto minorLabTestDto, String minorLabTestId) {
        MinorLabTest minorLabTest = minorLabTestRepository.getTestByTestId(minorLabTestId);
        if(minorLabTest != null){
            minorLabTest.setTestId(minorLabTestId);
            minorLabTest.setId(minorLabTest.getId());
            if(minorLabTestDto.getTestName() != null){
                minorLabTest.setTestName(minorLabTestDto.getTestName());
            }
            if(minorLabTestDto.getTestPrice() != null){
                minorLabTest.setTestPrice(minorLabTestDto.getTestPrice());
            }
            if(minorLabTestDto.getRemarks() != null){
                minorLabTest.setRemarks(minorLabTestDto.getRemarks());
            }
        }
//        minorLabTestRepository.updateTestByTestId(minorLabTestId, minorLabTest.getTestName(), minorLabTest.getTestPrice(),  minorLabTest.getRemarks());
        return minorLabTest;
    }
}
