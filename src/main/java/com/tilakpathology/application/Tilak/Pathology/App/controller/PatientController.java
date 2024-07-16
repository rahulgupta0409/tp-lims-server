package com.tilakpathology.application.Tilak.Pathology.App.controller;


import com.tilakpathology.application.Tilak.Pathology.App.constants.RegexpConstants;
import com.tilakpathology.application.Tilak.Pathology.App.dto.PatientDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.PatientResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1")
public class PatientController {

    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;


    @PostMapping(value = "/addPatient")
    @Operation(summary = "Save the Patient Info into the database.")
    public ResponseEntity<PatientResponseDto> addPatient(@RequestBody PatientDto patientDto){

        if(!RegexpConstants.EMAIL_REGEXP.matcher(patientDto.getEmailId()).matches()){
            log.info("Patient's Email Id: {} is not valid", patientDto.getEmailId());
            throw new BadRequestException("Email Id is not Valid");
        }
//        if(!RegexpConstants.PHONE_NUMBER_REGEXP.matcher(patientDto.getPhoneNumber()).matches()){
//            log.info("Patient's Phone Number: {} is not valid", patientDto.getPhoneNumber());
//            throw new BadRequestException("Phone Number is not Valid");
//        }

        PatientResponseDto patientResponse = patientService.addPatient(patientDto);
        return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
    }

}
