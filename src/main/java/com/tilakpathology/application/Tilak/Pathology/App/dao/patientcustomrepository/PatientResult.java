package com.tilakpathology.application.Tilak.Pathology.App.dao.patientcustomrepository;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResult {

    private String patientId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String phoneNumber;

    private String emailId;

    private String orgName;

    private String doctorName;
}
