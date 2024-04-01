package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.UUID;

public interface CourseDetailDto {

    @JsonProperty("courseId")
    public UUID getCourse_id();
    @JsonProperty("userId")
    public Long getUser_id();
    @JsonProperty("title")
    public String getTitle();
    @JsonProperty("authorName")
    public String getAuthor_name();
    @JsonProperty("thumbNail")
    public String getThumb_nail();
    @JsonProperty("category")
    public String getCategory();
    @JsonProperty("ratings")
    public Integer getRatings();
    @JsonProperty("language")
    public String getLanguage();
    @JsonProperty("price")
    public Integer getPrice();
    @JsonProperty("date")
    public Date getDate();
    @JsonProperty("IsHtmlCourse")
    public Boolean getIs_html_course();
}
