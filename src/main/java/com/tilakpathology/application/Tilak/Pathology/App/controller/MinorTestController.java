package com.tilakpathology.application.Tilak.Pathology.App.controller;

import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestUpdateDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MinorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.MinorLabTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
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
@RequestMapping("/v1/minortest")
public class MinorTestController {

    private final Logger log = LoggerFactory.getLogger(MinorTestController.class);

    @Autowired
    private MinorLabTestService minorLabTestService;

    @PostMapping(value = "/addMinorTest")
    @Operation(summary = "Save the Minor test Info into the database.")
    public ResponseEntity<MinorLabTest> addMinorTest(@RequestBody MinorLabTestDto minorLabTestDto) {

        if(minorLabTestDto.getTestName() == null || minorLabTestDto.getTestPrice() == null){
            log.info("Invalid Input of Minor Test TestName: {}, Test Price: {}"
                    , minorLabTestDto.getTestName(), minorLabTestDto.getTestPrice());
            throw new BadRequestException("Invalid Input of Minor Test");
        }
        MinorLabTest minorLabTest = minorLabTestService.addMinorLabTest(minorLabTestDto);
        return new ResponseEntity<>(minorLabTest, HttpStatus.CREATED);
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

    @PutMapping(value = "/updateMinorLabTestById")
    @Operation(summary = "Updating the minor lab test.")
    public ResponseEntity<MinorLabTest> updateMinorLabTestById(@RequestBody MinorLabTestUpdateDto minorLabTestDto, @RequestHeader("MinorLabTestId")String minorLabTestId){
        MinorLabTest minorLabTest = minorLabTestService.updateMinorLabTest(minorLabTestDto, minorLabTestId);
        return new ResponseEntity<>(minorLabTest, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteMinorLabTestBy/{testId}")
    @Operation(summary = "Deleting minor lab test.")
    public void deleteMinorLabTestById(@PathVariable String testId){
        minorLabTestService.deleteMinorLabTestById(testId);
    }
}


