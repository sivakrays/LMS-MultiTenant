package com.LMS.userManagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDto {

    public String email;

    public String password;

    public String role;

    public AdminDto(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}