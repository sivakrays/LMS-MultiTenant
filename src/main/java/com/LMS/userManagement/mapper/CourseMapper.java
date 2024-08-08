package com.LMS.userManagement.mapper;

import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.records.CourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO CourseToCourseDtoMapper(Course course, String profileImage, boolean isPurchased) {
        return new CourseDTO(
                course.getCourseId(),
                course.getUserId(),
                profileImage,
                course.getTitle(),
                course.getAuthorName(),
                course.getDescription(),
                course.getThumbNail(),
                course.getEnrolled(),
                course.getCategory(),
                course.getRatings(),
                course.getLanguage(),
                course.getOverview(),
                course.getWhatYouWillLearn(),
                course.getPrice(),
                course.getCreatedDate(),
                course.isFree(),
                course.isHtmlCourse(),
                isPurchased,
                course.getSections(),
                course.getChapters()

        );
    }
}
