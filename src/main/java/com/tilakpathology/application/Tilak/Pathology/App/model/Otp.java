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
@Document(collection = "user-otp")
@Schema(description = "This verifies the OTP to the users email for the verification.")
public class Otp {

    @org.springframework.data.annotation.Id
    private BigInteger Id;

    private String otpId;

    private Integer OTP;

    private String email;

    private String createDate;
}
