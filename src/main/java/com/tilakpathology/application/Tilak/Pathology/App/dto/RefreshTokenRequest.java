package com.tilakpathology.application.Tilak.Pathology.App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RefreshTokenRequest {

    private String refreshToken;
}
