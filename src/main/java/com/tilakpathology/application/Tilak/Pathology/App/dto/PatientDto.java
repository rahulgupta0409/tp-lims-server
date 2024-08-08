package com.tilakpathology.application.Tilak.Pathology.App.dto;


import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private String firstName;

    private String lastName;

    private Integer age;

    private String phoneNumber;

    private String emailId;

    private List<MinorLabTest> labTests;

    private String org;

    private String referredDoctorId;

    private Boolean isUpi;

    private Integer totalAmount;

    private Integer discount;

    private Integer paidAmount;

    private Integer dueAmount;

    private String createdBy;

    private String createdDate;

    private String updatedBy;

    private String updatedDate;
}
