package com.LMS.userManagement.records;

import com.LMS.userManagement.model.Course;

import java.util.List;

public record PopularCourse(
        String title,
        List<Course> data,

        boolean isEducation

) {
}
