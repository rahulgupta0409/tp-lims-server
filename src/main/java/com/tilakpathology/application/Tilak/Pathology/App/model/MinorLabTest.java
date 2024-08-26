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
@Document(collection = "minor-lab-tests")
@Schema(description = "All details about the minor Lab Tests.")
public class MinorLabTest {

    @Id
    private BigInteger Id;

    private String testId;

    private String testName;

    private Float testPrice;

    private String minorTestUnit;

    private String remarks;

    private Integer minAge;

    private Integer maxAge;

    private String defaultValue;

    private String createdOn;

    private String createdBy;

    private String updatedOn;

    private String updatedBy;
}
