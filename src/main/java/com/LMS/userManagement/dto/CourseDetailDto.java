package com.LMS.userManagement.dto;

import jakarta.persistence.Column;

import java.util.Date;
import java.util.UUID;

public interface CourseDetailDto {

    public UUID getCourse_id();
    public Long gteUser_id();
    public String getTitle();
    public String getAuthor_name();
    public String getThumb_nail();
    public String getCategory();
    public Integer getRatings();
    public String getLanguage();
    public Integer getPrice();
    public Date getDate();
    public Boolean getIs_html_course();
}
