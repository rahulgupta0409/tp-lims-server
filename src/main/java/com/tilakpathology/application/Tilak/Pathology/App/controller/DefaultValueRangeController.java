package com.tilakpathology.application.Tilak.Pathology.App.controller;

import com.tilakpathology.application.Tilak.Pathology.App.dto.DefaultValueRangeDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.DefaultValueRange;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.DefaultValueRangeService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1/valueRange")
public class DefaultValueRangeController {

    private final Logger log = LoggerFactory.getLogger(DefaultValueRangeController.class);

    @Autowired
    private DefaultValueRangeService defaultValueRangeService;

    @PostMapping(value = "/addDefaultValueRange")
    @Operation(summary = "Save the test range value Info into the database.")
    public ResponseEntity<DefaultValueRange> addDefaultValueRange(@RequestBody DefaultValueRangeDto defaultValueRange) {
        DefaultValueRange defaultValueRangeResponse = defaultValueRangeService.addDefaultValueRange(defaultValueRange);
        log.info("Default value range saved with range: {}-{} , value: {}", defaultValueRangeResponse.getMinAge(),
                defaultValueRangeResponse.getMaxAge(), defaultValueRangeResponse.getDefaultValue());
        return new ResponseEntity<>(defaultValueRangeResponse, HttpStatus.CREATED);
    }
}
