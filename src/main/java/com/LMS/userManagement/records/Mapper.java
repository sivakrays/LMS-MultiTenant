package com.LMS.userManagement.records;

import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.dto.UserDto;
import com.LMS.userManagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@RequiredArgsConstructor
public class Mapper {

    private final PasswordEncoder passwordEncoder;



    // user register details mapper
    public User UserMapper(RegisterRequest request){
        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .confirmPassword(passwordEncoder.encode(request.getConfirmPassword()))
                .role(request.getRole().toLowerCase())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .build();

        return user;
    }

    //saved user in user registration using records
    public UserDTO UserDTOMapper(User savedUser){
        UserDTO userDto= new UserDTO(
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.role,
                savedUser.createdDate
        );

        return userDto;
    }

}
