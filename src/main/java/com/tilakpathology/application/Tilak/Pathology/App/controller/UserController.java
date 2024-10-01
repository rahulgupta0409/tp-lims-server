package com.tilakpathology.application.Tilak.Pathology.App.controller;


import com.tilakpathology.application.Tilak.Pathology.App.config.securityconfig.JwtService;
import com.tilakpathology.application.Tilak.Pathology.App.dto.RefreshTokenRequest;
import com.tilakpathology.application.Tilak.Pathology.App.dto.UserDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.UserSignInDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.LoginResponse;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.UserResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.User;
import com.tilakpathology.application.Tilak.Pathology.App.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;



    @PostMapping(path="/signup")
    public ResponseEntity<User> SignUp(@RequestBody UserDto userDto){
//        if(userDto.getPassword() != userDto.getConfirmPassword()){
//            throw new BadRequestException("Password and confirm password should be same.");
//        }
        User userResponse = userService.SignUp(userDto);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping(path="/validateOtp")
    public ResponseEntity<UserResponseDto> SignupValidation(@RequestParam Integer otp, @RequestBody User user){
        if(user == null || otp == null){
            throw new BadRequestException("The OTP or user is required.");
        }
        UserResponseDto userResponseDto = userService.ValidateOTP(otp, user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<LoginResponse> SignIn(@RequestBody UserSignInDto userSignInDto){
        if(userSignInDto.getUsername() == null || userSignInDto.getPassword() == null){
            throw new BadRequestException("SignIn Credentials are not valid.");
        }


        User user = userService.SignIn(userSignInDto);
        LoginResponse loginResponse = new LoginResponse();

        if(user != null){
            String jwtToken = jwtService.generateToken(user);

            String jwtRefreshtoken = jwtService.generateRefreshToken(user);

            loginResponse.setUser(user);
            loginResponse.setToken(jwtToken);
            loginResponse.setRefreshToken(jwtRefreshtoken);
            loginResponse.setJwtRefreshTokenExpiresIn(jwtService.getJwtRefreshTokenExpirationTime());
            loginResponse.setJwtTokenExpiresIn(jwtService.getExpirationTime());
        }
        System.out.println(loginResponse);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        LoginResponse loginResponse = new LoginResponse();
        String username = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
        if (jwtService.validateToken(refreshTokenRequest.getRefreshToken(), username)) {
            String newJwtToken = jwtService.generateToken(username);
            String newRefreshToken = jwtService.generateRefreshToken(username);
            loginResponse.setToken(newJwtToken);
            loginResponse.setRefreshToken(newRefreshToken);
            loginResponse.setJwtRefreshTokenExpiresIn(jwtService.getJwtRefreshTokenExpirationTime());
            loginResponse.setJwtTokenExpiresIn(jwtService.getExpirationTime());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        throw new RuntimeException("Invalid refresh token");
    }
}
