package com.tilakpathology.application.Tilak.Pathology.App.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private String userId;

    private String emailId;

    private String userName;

    private String fullName;

    private String password;

    private String timestamp;
}
