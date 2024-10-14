package com.tilakpathology.application.Tilak.Pathology.App.controller;


import com.tilakpathology.application.Tilak.Pathology.App.constants.RegexpConstants;
import com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository.PatientResult;
import com.tilakpathology.application.Tilak.Pathology.App.dto.PatientDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.PatientResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/v1/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;



//    @PostMapping(value = "/addPatient")
//    @Operation(summary = "Save the Patient Info into the database.")
//    public ResponseEntity<CompletableFuture<PatientResponseDto>> addPatient(@RequestBody PatientDto patientDto){
//
//        if(!RegexpConstants.EMAIL_REGEXP.matcher(patientDto.getEmailId()).matches()){
//            log.info("Patient's Email Id: {} is not valid", patientDto.getEmailId());
//            throw new BadRequestException("Email Id is not Valid");
//        }
////        if(!RegexpConstants.PHONE_NUMBER_REGEXP.matcher(patientDto.getPhoneNumber()).matches()){
////            log.info("Patient's Phone Number: {} is not valid", patientDto.getPhoneNumber());
////            throw new BadRequestException("Phone Number is not Valid");
////        }
//
//        CompletableFuture<PatientResponseDto> patientResponse = patientService.addPatient(patientDto);
//        return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
//    }

    @PostMapping(value = "/addPatient")
    @Operation(summary = "Save the Patient Info into the database.")
    public ResponseEntity<PatientResponseDto> addPatient(@RequestBody PatientDto patientDto) {

        if(!RegexpConstants.EMAIL_REGEXP.matcher(patientDto.getEmailId()).matches()){
            log.info("Patient's Email Id: {} is not valid", patientDto.getEmailId());
            throw new BadRequestException("Email Id is not Valid");
        }
        patientService.addPatient(patientDto);
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setFirstName(patientDto.getFirstName());

        return new ResponseEntity<>(patientResponseDto, HttpStatus.CREATED);
//        return patientService.addPatient(patientDto)
//                .thenApply(patient -> {
//                    PatientResponseDto patientResponseDto = new PatientResponseDto();
//                    patientResponseDto.setFirstName(patient.getFirstName());
//                    return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDto);
//                })
//                .exceptionally(ex -> {
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                            .body(null);
//                });
    }

    @GetMapping(value = "/getAllPatients")
    @Operation(summary = "Fetching all Patients.")
    public ResponseEntity<List<?>> getAllPatients() {
        List<?> patientResultList = patientService.getAllPatients();
        return new ResponseEntity<>(patientResultList, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllPatientsByDateRange")
    @Operation(summary = "Fetching all Patients.")
    public ResponseEntity<List<?>> getAllPatientsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        List<?> patientResultList = patientService.getPatientsByStartEndDate(startDate, endDate);

        return new ResponseEntity<>(patientResultList, HttpStatus.OK);
    }

    @GetMapping(value = "/searchPatients")
    @Operation(summary = "Fetching all Patients by Search")
    public ResponseEntity<List<?>> searchPatients(@RequestParam String query){
        List<?> patientList = patientService.searchPatients(query);
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @PutMapping("/{patientId}/tests/{testId}")
    public void updateTestValue(
            @PathVariable String patientId,
            @PathVariable String testId,
            @RequestParam String value) {
        patientService.updateTestForPatient(patientId, testId, value);
    }

}
