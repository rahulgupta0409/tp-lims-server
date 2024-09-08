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
@Document(collection = "organizations")
@Schema(description = "All details about the Organizations.")
public class Organization {

    @Id
    private BigInteger Id;

    private String orgId;

    private String orgName;

    private String orgDetails;

    private String createdBy;

    private String createdOn;
}
