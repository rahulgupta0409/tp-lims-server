package com.tilakpathology.application.Tilak.Pathology.App.exceptions.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.RegexpConstants.DATE_MONTH_PATTERN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String errorMessage;

    private String errorCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_MONTH_PATTERN)
    private LocalDateTime timestamp;
}
