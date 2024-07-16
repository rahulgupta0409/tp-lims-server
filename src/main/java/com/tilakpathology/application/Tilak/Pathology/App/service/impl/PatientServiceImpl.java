package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.PatientRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.PatientDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.PatientResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.Patient;
import com.tilakpathology.application.Tilak.Pathology.App.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;

@Service
public class PatientServiceImpl implements PatientService {

    private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;



    @Override
    public PatientResponseDto addPatient(PatientDto patientDto) throws BadRequestException {
        Patient patient = Patient.builder()
                .patientId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .firstName(patientDto.getFirstName()).lastName(patientDto.getLastName())
                .age(patientDto.getAge())
                .phoneNumber(patientDto.getPhoneNumber())
                .emailId(patientDto.getEmailId())
                .createdDate(LocalDateTime.now().toString())
                .build();
        patientRepository.save(patient);
        log.info("Patient Successfully Created With PatientId {}", patient.getPatientId());
        return PatientResponseDto.builder().patientId(patient.getPatientId())
                .firstName(patient.getFirstName()).lastName(patient.getLastName())
                .age(patient.getAge())
                .phoneNumber(patient.getPhoneNumber())
                .emailId(patient.getEmailId())
                .createdDate(patient.getCreatedDate())
                .build();
    }
}
