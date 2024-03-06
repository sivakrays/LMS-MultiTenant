package com.LMS.userManagement.records;

import java.util.List;

public record EducationCourse(
        String title,
        List<CourseData> data,

        boolean isEducation
) {
}
