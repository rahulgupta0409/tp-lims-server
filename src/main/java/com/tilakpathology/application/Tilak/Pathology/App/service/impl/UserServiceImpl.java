package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.RoleRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dao.UserRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.UserDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.UserSignInDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.UserResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.model.Enums.UserRole;
import com.tilakpathology.application.Tilak.Pathology.App.model.Role;
import com.tilakpathology.application.Tilak.Pathology.App.model.User;
import com.tilakpathology.application.Tilak.Pathology.App.service.OtpService;
import com.tilakpathology.application.Tilak.Pathology.App.service.OtpServiceImpl;
import com.tilakpathology.application.Tilak.Pathology.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OtpServiceImpl otpService;

    @Override
    public UserResponseDto SignUp(UserDto userDto) {
        User user = userRepository.getUserByUserName(userDto.getUserName());

        Set<String> strRoles = userDto.getRoles();
        Set<Role> roles = new HashSet<>();
        if(strRoles == null){
            Role userRole = roleRepository.findByName(UserRole.ROLE_VIEWER);
            roles.add(userRole);
        }


//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(UserRole.ROLE_VIEWER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "super_admin":
//                        Role superAdminRole = roleRepository.findByName(UserRole.ROLE_SUPER_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(superAdminRole);
//
//                        break;
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "user":
//                        Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//
//                        break;
//                    default:
//                        Role viewerRole = roleRepository.findByName(UserRole.ROLE_VIEWER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(viewerRole);
//                }
//            });
//        }


        if(user == null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(userDto.getPassword());
            User userBuild = User.builder()
                    .userId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                    .emailId(userDto.getEmailId())
                    .fullName(userDto.getFullName())
                    .userName(userDto.getUserName())
                    .roles(roles)
                    .password(password)
                    .timestamp(LocalDateTime.now().toString())
                    .build();
            otpService.generateOtp(userDto.getEmailId());
//            userRepository.save(userBuild);
            System.out.println(user);
            return UserResponseDto.builder()
                    .userId(userBuild.getUserId())
                    .emailId(userBuild.getEmailId())
                    .fullName(userBuild.getFullName())
                    .userName(userBuild.getUsername())
                    .timestamp(userBuild.getTimestamp())
                    .build();
        }
        throw new BadRequestException("The Account is already exists");
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
