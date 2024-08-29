package com.tilakpathology.application.Tilak.Pathology.App.controller;


import com.tilakpathology.application.Tilak.Pathology.App.dto.DefaultValueRangeDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.DoctorDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.DefaultValueRange;
import com.tilakpathology.application.Tilak.Pathology.App.model.Doctors;
import com.tilakpathology.application.Tilak.Pathology.App.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value = "/addDoctor")
    @Operation(summary = "Save the doctor Info into the database.")
    public ResponseEntity<Doctors> addDoctor(@RequestBody DoctorDto doctorDto) {
        Doctors doctors = doctorService.createDoctor(doctorDto);
        log.info("Doctor with Doctor Name: {} is created.", doctors.getDoctorName());
        return new ResponseEntity<>(doctors, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllDoctors")
    @Operation(summary = "Fetching all Doctors.")
    public ResponseEntity<List<?>> getAllDoctors() {
        List<?> doctorsList = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctorsList, HttpStatus.OK);
    }
}
