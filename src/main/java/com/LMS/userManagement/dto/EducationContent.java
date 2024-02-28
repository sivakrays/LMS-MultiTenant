package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface EducationContent {

    public String getImage();

    @JsonProperty("imageContent")
    public String getImage_content();
}
