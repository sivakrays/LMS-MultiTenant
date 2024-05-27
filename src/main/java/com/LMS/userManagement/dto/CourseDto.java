package com.LMS.userManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    public String courseId;
    public Long userId;
    public String title;
    public String authorName;
    public String profileImage;
    public String thumbNail;
    public String category;
    public Integer ratings;
    public String language;
    public Integer  price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Timestamp createdDate;
    public Boolean isHtmlCourse;
    public Boolean isFree;
    public Boolean isPurchased;

}
