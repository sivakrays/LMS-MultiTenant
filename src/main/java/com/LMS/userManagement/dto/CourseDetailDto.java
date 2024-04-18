package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public interface CourseDetailDto {


    @JsonProperty("courseId")
    public String  getCourse_id();
    @JsonProperty("userId")
    public Long getUser_id();
    @JsonProperty("title")
    public String getTitle();
    @JsonProperty("authorName")
    public String getAuthor_name();
    @JsonProperty("profileImage")
    public String getProfile_image();
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
    @JsonProperty("createdDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Timestamp getCreated_date();
    @JsonProperty("isHtmlCourse")
    public Boolean getIs_html_course();
    @JsonProperty("isFree")
    public Boolean getIs_free();
    @JsonProperty("isPurchased")
    public Boolean getPurchased();
}
