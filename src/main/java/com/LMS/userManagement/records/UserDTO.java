package com.LMS.userManagement.records;

import java.sql.Timestamp;

public record UserDTO(Long id,String name, String email, String role, Timestamp createdDate,int standard) {
}
