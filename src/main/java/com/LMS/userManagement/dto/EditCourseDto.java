package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCourseDto {

    private String courseId;
    private String title;
    private String description;
    private String category;
    private Integer price;
    private Integer discountPercentage;
    private String language;
    private String thumbNail;
    private String whatYouWillLearn;
    private Boolean isHtmlCourse;
    private Boolean isFree;
    private String visibleTo;
    private LocalDateTime updatedTime;

}