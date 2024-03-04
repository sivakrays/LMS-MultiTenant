package com.LMS.userManagement.util;


import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomMapperTest {

    private CustomMapper customMapper;

    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        passwordEncoder=new BCryptPasswordEncoder();
        customMapper=new CustomMapper(passwordEncoder);
    }

    @Test
    public void shouldMapUserDtoToUser(){

        RegisterRequest request=new RegisterRequest("siva",
                "siva@gmail.com",
                "siva@123",
                "siva@123",
                "admin"
                );

        User user=customMapper.UserMapper(request);

        assertEquals(request.getName(),user.getName());
        assertEquals(request.getEmail(),user.getEmail());
        assertEquals(request.getRole(),user.getRole());
    }


}