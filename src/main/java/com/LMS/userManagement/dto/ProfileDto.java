package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    public Long id;
    public String name;
    public String gender;
    public String school;
    public Integer standard;
    public String city;
    public String country;
}
