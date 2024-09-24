package com.tilakpathology.application.Tilak.Pathology.App.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportProgressDto {

    private String patientId;

    private Integer progress;

    private List<Map<String, String>> working;

}
