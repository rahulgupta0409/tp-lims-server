package com.tilakpathology.application.Tilak.Pathology.App.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "report-progress")
@Schema(description = "Progress of Reporting.")
public class ReportProgress {

    @Id
    private BigInteger Id;

    private String progressId;

    private String patientId;

//    @Min(value = 0, message = "The minimum value of the progress is 0")
//    @Max(value = 100, message = "The maximum value of the progress is 100")
    private Integer progress;

    private List<Map<String, String>> working;

    private String createdDate;

    private String updatedDate;
}
