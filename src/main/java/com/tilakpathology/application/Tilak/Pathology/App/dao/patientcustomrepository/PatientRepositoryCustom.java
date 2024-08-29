package com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository;

import java.util.List;

public interface PatientRepositoryCustom {

    List<PatientResult> findPatientsWithOrgNames();

}
