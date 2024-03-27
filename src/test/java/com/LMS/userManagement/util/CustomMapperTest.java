package com.LMS.userManagement.util;


import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.mapper.CustomMapper;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.records.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        User user=customMapper.DtoToUserMapper(request);

        assertEquals(request.getName(),user.getName());
        assertEquals(request.getEmail(),user.getEmail());
        assertEquals(request.getRole(),user.getRole());
    }

    @Test
    public void shouldMapUserToUserDTO(){

        User user=new User();
        user.setName("siva");
        user.setEmail("siva@gmail.com");
        user.setRole("admin");
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        UserDTO userDTO=customMapper.UserDtoToUserMapper(user);

        assertNotNull(userDTO);
        assertEquals(user.getName(),userDTO.name());
    }



}