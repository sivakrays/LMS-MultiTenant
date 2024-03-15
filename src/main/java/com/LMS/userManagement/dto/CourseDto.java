package com.LMS.userManagement.dto;

import com.LMS.userManagement.model.Chapter;
import com.LMS.userManagement.model.Section;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    public String courseId;
    public Long userId;
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
    private Timestamp date;
    @JsonProperty("isHtmlCourse")
    private Boolean isHtmlCourse;
    @JsonProperty("isPurchased")
    private Boolean isPurchased;
    @JsonProperty("isFree")
    private Boolean isFree;
    private List<Chapter> chapters;
    private List<Section> sections;
}
