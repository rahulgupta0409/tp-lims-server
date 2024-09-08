package com.tilakpathology.application.Tilak.Pathology.App.dto;


import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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

    private Set<String> labTestIds;

    private String orgId;

    private String doctorId;

    private Boolean isUpi;

    private Boolean isOutSampled;

    private Integer totalAmount;

    private Integer discount;

    private Integer paidAmount;

    private Integer dueAmount;

    private String createdBy;

}
