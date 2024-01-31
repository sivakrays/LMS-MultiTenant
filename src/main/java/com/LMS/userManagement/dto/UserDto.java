package com.LMS.userManagement.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserDto {

    private String email;

    private String role;

    private String name;

    private Timestamp createdDate;
}
