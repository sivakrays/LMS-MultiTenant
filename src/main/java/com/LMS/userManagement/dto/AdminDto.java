package com.LMS.userManagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDto {

    private String email;

    private String password;

    private String role;
}
