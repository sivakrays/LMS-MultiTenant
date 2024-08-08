package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface UserProfileDto {

    @JsonProperty("userId")
    Long getId();

    String getName();

    String getEmail();

    String getGender();

    String getSchool();

    Integer getStandard();

    String getCity();

    String getCountry();

    @JsonProperty("profileImage")
    String getProfile_image();

}
