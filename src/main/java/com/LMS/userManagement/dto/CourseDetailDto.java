package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.sql.Timestamp;
import java.util.Date;

public interface CourseDetailDto {

    @JsonProperty("courseId")
    public String  getCourse_id();
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
    public int getPrice();
    @JsonProperty("CreatedDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    public Timestamp getCreated_date();
    @JsonProperty("isHtmlCourse")
    public boolean getIs_html_course();
    @JsonProperty("isFree")
    public boolean getIs_free();
    @JsonProperty("purchased")
    public boolean getPurchased();


}
