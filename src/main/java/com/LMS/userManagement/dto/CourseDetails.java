package com.LMS.userManagement.dto;

import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.HtmlCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetails {
    public Course courseData;
    public List<HtmlCourse> HtmlData;
}
