package com.tilakpathology.application.Tilak.Pathology.App.dto.response;


import com.tilakpathology.application.Tilak.Pathology.App.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private String userId;

    private String emailId;

    private String userName;

    private String fullName;

    private List<Role> roles;

    private String timestamp;
}
