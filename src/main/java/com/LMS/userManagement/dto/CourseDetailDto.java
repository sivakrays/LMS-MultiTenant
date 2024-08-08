package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public interface CourseDetailDto {

    @JsonProperty("courseId")
    String getCourse_id();

    @JsonProperty("userId")
    Long getUser_id();

    @JsonProperty("title")
    String getTitle();

    @JsonProperty("authorName")
    String getAuthor_name();

    @JsonProperty("profileImage")
    String getProfile_image();

    @JsonProperty("thumbNail")
    String getThumb_nail();

    @JsonProperty("category")
    String getCategory();

    @JsonProperty("ratings")
    Integer getRatings();

    @JsonProperty("language")
    String getLanguage();

    @JsonProperty("price")
    Integer getPrice();

    @JsonProperty("createdDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Timestamp getCreated_date();

    @JsonProperty("isHtmlCourse")
    Boolean getIs_html_course();

    @JsonProperty("isFree")
    Boolean getIs_free();

    @JsonProperty("isPurchased")
    Boolean getPurchased();

}
