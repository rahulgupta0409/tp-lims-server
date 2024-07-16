package com.tilakpathology.application.Tilak.Pathology.App.dto.response;

import com.tilakpathology.application.Tilak.Pathology.App.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private User user;

    private String token;

    private Long expiresIn;
}
