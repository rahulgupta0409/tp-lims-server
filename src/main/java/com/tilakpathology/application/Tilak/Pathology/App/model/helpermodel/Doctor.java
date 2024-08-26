package com.tilakpathology.application.Tilak.Pathology.App.model.helpermodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    private String referredDoctorId;

    private String referredDoctorName;
}
