package com.tilakpathology.application.Tilak.Pathology.App.dto;

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
public class DoctorDto {

    private String doctorName;

    private Set<String> orgId;

    private String doctorDetails;

    private String createdBy;
}
