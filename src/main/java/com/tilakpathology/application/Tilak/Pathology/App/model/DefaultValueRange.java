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
@Document(collection = "default-value-range")
@Schema(description = "All details of default test value for TestIds.")
public class DefaultValueRange {

    @Id
    private BigInteger Id;

    private String defaultValueRangeId;

    private String testId;

    private Integer minAge;

    private Integer maxAge;

    private String defaultValue;

    private String defaultRemark;
}
