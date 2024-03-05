package com.LMS.userManagement.records;

import com.LMS.userManagement.dto.EducationContent;

import java.util.List;

public record FeaturedCourse(
        String title,
        List<CourseData> data,

        boolean isEducation
) {
}
