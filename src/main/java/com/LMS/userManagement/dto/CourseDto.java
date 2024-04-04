package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    public String courseId;
    public String title;
    public String authorName;
    public String description;
    public String thumbNail;
    public Long enrolled;
    public String category;
    public Integer ratings;
    private String language;
    private String overview;
    private String whatYouWillLearn;
    private Integer price;
    private Date createdDate;
    private ArrayList<SectionDto> sections;
}
