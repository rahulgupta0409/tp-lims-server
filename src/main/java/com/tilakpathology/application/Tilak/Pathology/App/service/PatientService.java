package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.PatientDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.PatientResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.Patient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PatientService {

    CompletableFuture<PatientResponseDto> addPatient(PatientDto patientDto);

    List<?> getAllPatients();

    List<?> getPatientsByStartEndDate(String startDate, String endDate);


}
