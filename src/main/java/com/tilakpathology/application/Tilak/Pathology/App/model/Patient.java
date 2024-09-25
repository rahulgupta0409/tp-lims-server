package com.tilakpathology.application.Tilak.Pathology.App.model;

import com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel.Doctor;
import com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel.Org;
import com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel.Tests;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "patients")
@Schema(description = "All details about the Patient.")
public class Patient {

    @Id
    private BigInteger Id;

    private Integer patientLabId;

    private String patientId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    private String phoneNumber;

    private String emailId;

    private List<Tests> tests;

    private Org org;

    private Doctor referredDoctor;

    private Boolean isUpi;

    private Boolean isOutSampled;

    private Integer totalAmount;

    private Integer discount;

    private Integer paidAmount;

    private Integer dueAmount;

    private String createdBy;

    private String createdDate;

    private String updatedBy;

    private String updatedDate;
}




