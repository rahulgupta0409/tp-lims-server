package com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository;

import com.tilakpathology.application.Tilak.Pathology.App.model.Patient;

import java.util.List;

public interface PatientRepositoryCustom {

    List<PatientResult> findPatientsWithOrgNames();

    void updateTestForPatient(Patient patient);

}
