package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.UserRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.UserDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.UserSignInDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.UserResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.User;
import com.tilakpathology.application.Tilak.Pathology.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto SignUp(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(userDto.getPassword());
        User user = User.builder()
                .userId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .emailId(userDto.getEmailId())
                .fullName(userDto.getFullName())
                .userName(userDto.getUserName())
                .password(password)
                .timestamp(LocalDateTime.now().toString())
                .build();
        userRepository.save(user);
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .emailId(user.getEmailId())
                .fullName(user.getFullName())
                .userName(user.getUsername())
                .password(user.getPassword())
                .timestamp(user.getTimestamp())
                .build();
    }

    @Override
    public User SignIn(UserSignInDto userSignInDto) {
        User user = userRepository.getUserByUserName(userSignInDto.getUserName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(user != null){
            if(passwordEncoder.matches(userSignInDto.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new BadRequestException("The Account does not Exists.");
    }
}
