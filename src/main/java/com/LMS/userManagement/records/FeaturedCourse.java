package com.LMS.userManagement.records;

import com.LMS.userManagement.dto.EducationContent;

import java.util.List;

public record FeaturedCourse(
        String courseTitle1,
        String courseTitle2,
        String educationTitle,
        List<EducationContent> educationContent
) {
}
