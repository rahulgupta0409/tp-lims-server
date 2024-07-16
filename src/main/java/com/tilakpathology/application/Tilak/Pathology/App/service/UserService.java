package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.UserDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.UserSignInDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.UserResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.User;

import java.util.Optional;


public interface UserService {

    UserResponseDto SignUp(UserDto userDto);

    User SignIn(UserSignInDto userSignInDto);
}
