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



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "major-lab-tests")
@Schema(description = "All details about the major Lab Tests.")
public class MajorLabTest {

    @Id
    private BigInteger Id;

    private String majorTestId;

    private String majorTestName;

    private List<MinorLabTest> minorLabTestList;

    private Float majorTestPrice;

    private String majorTestRemarks;

    private String createdOn;

    private String createdBy;

    private String updatedOn;

    private String updatedBy;
}
