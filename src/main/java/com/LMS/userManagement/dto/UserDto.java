package com.LMS.userManagement.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data

public class UserDto {

    public String email;

    public String role;

    public String name;

    public Timestamp createdDate;
}
