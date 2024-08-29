package com.tilakpathology.application.Tilak.Pathology.App.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "doctors")
@Schema(description = "All details about the Doctors.")
public class Doctors {

    @Id
    private BigInteger Id;

    private String doctorId;

    private String doctorName;

    private Set<String> orgId;

    private String doctorDetails;

    private String createdDate;

    private String createdBy;

    private String updatedDate;

    private String updatedBy;
}
