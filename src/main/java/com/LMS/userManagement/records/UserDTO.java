package com.LMS.userManagement.records;

import java.sql.Timestamp;

public record UserDTO(String name, String email, String role, Timestamp createdDate) {
}
