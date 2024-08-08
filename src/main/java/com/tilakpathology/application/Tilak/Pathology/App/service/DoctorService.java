package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.DoctorDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.Doctors;

public interface DoctorService {

    void createDoctor(DoctorDto doctorDto);

    Doctors getAllDoctors();

    Doctors getDoctorById(String doctorId);

    Doctors getDoctorByOrganizationId(String organizationId);

    void updateDoctor(DoctorDto doctorDto);

    void deleteDoctor(String doctorId);
}
