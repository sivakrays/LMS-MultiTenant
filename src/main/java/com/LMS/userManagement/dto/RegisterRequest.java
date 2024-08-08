package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private String confirmPassword;

    private String role;

    private int standard;

    private String school;

}
