package com.tilakpathology.application.Tilak.Pathology.App.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "patients")
@Schema(description = "All details about the Patient.")
public class Patient {

    @Id
    private BigInteger Id;

    private String patientId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String phoneNumber;

    private String emailId;

    private String createdDate;

    private String updatedDate;
}
