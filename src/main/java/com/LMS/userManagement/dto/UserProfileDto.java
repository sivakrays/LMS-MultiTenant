package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface UserProfileDto {

    @JsonProperty("userId")
    public Long getId();

    public String getName();

    public String getEmail();

    public String getGender();

    public String getSchool();

    public Integer getStandard();

    public String getCity();

    public String getCountry();



}
