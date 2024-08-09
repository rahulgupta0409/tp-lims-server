package com.tilakpathology.application.Tilak.Pathology.App.controller;


import com.tilakpathology.application.Tilak.Pathology.App.dto.MajorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MajorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.MajorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.MajorLabTestService;
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
@RequestMapping("/v1/majortest")
public class MajorLabTestController {


    private final Logger log = LoggerFactory.getLogger(MajorLabTestController.class);

    @Autowired
    private MajorLabTestService majorLabTestService;


    @PostMapping(value = "/addMajorLabTest")
    @Operation(summary = "Save the Major test Info into the database.")
    public ResponseEntity<MajorLabTest> addMajorLabTest(@RequestBody MajorLabTestDto majorLabTestDto) {

        if(majorLabTestDto.getMajorTestName() == null || majorLabTestDto.getMajorTestRemarks() == null){
            log.info("Invalid Input of Major Test TestName: {}, Test Remark: {}"
                    , majorLabTestDto.getMajorTestName(), majorLabTestDto.getMajorTestRemarks());
            throw new BadRequestException("Invalid Input of Major Test");
        }
        MajorLabTest majorLabTest = majorLabTestService.addMajorLabTest(majorLabTestDto);
        return new ResponseEntity<>(majorLabTest, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllMajorTests")
    @Operation(summary = "Fetching all Major Tests.")
    public ResponseEntity<List<MajorLabTest>> getAllMajorTests() {
        List<MajorLabTest> majorLabTestList = majorLabTestService.getAllMajorLabTests();
        return new ResponseEntity<>(majorLabTestList, HttpStatus.OK);
    }

    @GetMapping(value = "/getMajorTestsByTestId")
    @Operation(summary = "Fetching Major Test by Test Id.")
    public ResponseEntity<Optional<MajorLabTestResponseDto>> getMinorTestByTestId(@RequestParam String majorLabTestId) {
        Optional<MajorLabTestResponseDto> majorLabTestResponseDto = majorLabTestService.getAllMajorLabTestByTestId(majorLabTestId);
        return new ResponseEntity<>(majorLabTestResponseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/updateMajorLabTestById")
    @Operation(summary = "Updating the major lab test.")
    public ResponseEntity<MajorLabTest> updateMinorLabTestById(@RequestBody MajorLabTestDto majorLabTestDto, @RequestHeader("MajorLabTestId")String majorLabTestId){
        MajorLabTest majorLabTest = majorLabTestService.updateMajorLabTest(majorLabTestDto, majorLabTestId);
        return new ResponseEntity<>(majorLabTest, HttpStatus.OK);
    }
}
