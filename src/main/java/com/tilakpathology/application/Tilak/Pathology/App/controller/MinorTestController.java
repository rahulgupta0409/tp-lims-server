package com.tilakpathology.application.Tilak.Pathology.App.controller;

import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MinorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.MinorLabTestService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1")
public class MinorTestController {

    private final Logger log = LoggerFactory.getLogger(MinorTestController.class);

    @Autowired
    private MinorLabTestService minorLabTestService;

    @PostMapping(value = "/addMinorTest")
    @Operation(summary = "Save the Minor test Info into the database.")
    public ResponseEntity<MinorLabTestResponseDto> addMinorTest(@RequestBody MinorLabTestDto minorLabTestDto) {

        if(minorLabTestDto.getTestName() == null || minorLabTestDto.getTestPrice() == null){
            log.info("Invalid Input of Minor Test TestName: {}, Test Price: {}"
                    , minorLabTestDto.getTestName(), minorLabTestDto.getTestPrice());
            throw new BadRequestException("Invalid Input of Minor Test");
        }
        MinorLabTestResponseDto minorLabTestResponseDto = minorLabTestService.addMinorLabTest(minorLabTestDto);
        return new ResponseEntity<>(minorLabTestResponseDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllMinorTests")
    @Operation(summary = "Fetching all Minor Tests.")
    public ResponseEntity<List<MinorLabTest>> getAllMinorTests() {
        List<MinorLabTest> minorLabTestList = minorLabTestService.getAllMinorLabTests();
        return new ResponseEntity<>(minorLabTestList, HttpStatus.OK);
    }

    @GetMapping(value = "/getMinorTestsByTestId")
    @Operation(summary = "Fetching Minor Test by Test Id.")
    public ResponseEntity<Optional<MinorLabTestResponseDto>> getMinorTestByTestId(@RequestParam String testId) {
        Optional<MinorLabTestResponseDto> minorLabTestResponseDto = minorLabTestService.getAllMinorLabTestByTestId(testId);
        return new ResponseEntity<>(minorLabTestResponseDto, HttpStatus.OK);
    }
}


