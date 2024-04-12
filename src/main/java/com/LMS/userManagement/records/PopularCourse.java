package com.LMS.userManagement.records;

import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.dto.CourseDto;
import com.LMS.userManagement.model.Course;

import java.util.LinkedList;
import java.util.List;

public record PopularCourse(
        String title,
        LinkedList<CourseDto> data,

        boolean isEducation

) {
}
