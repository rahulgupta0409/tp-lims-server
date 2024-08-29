package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.DoctorDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.Doctors;

import java.util.List;

public interface DoctorService {

    Doctors createDoctor(DoctorDto doctorDto);

    List<Doctors> getAllDoctors();

    Doctors getDoctorById(String doctorId);

    Doctors getDoctorByOrganizationId(String organizationId);

    void updateDoctor(DoctorDto doctorDto);

    void deleteDoctor(String doctorId);
}
