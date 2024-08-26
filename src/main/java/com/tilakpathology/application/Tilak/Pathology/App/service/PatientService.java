package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.PatientDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.PatientResponseDto;

import java.util.concurrent.CompletableFuture;

public interface PatientService {

    CompletableFuture<PatientResponseDto> addPatient(PatientDto patientDto);
}
