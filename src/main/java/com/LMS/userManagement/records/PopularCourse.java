package com.LMS.userManagement.records;

import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.model.Course;

import java.util.List;

public record PopularCourse(
        String title,
        List<CourseDetailDto> data,

        boolean isEducation

) {
}
