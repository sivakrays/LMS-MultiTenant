package com.LMS.userManagement.records;

import com.LMS.userManagement.model.Chapter;
import com.LMS.userManagement.model.Section;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.ArrayList;


public record CourseDTO(
        String courseId,
        Long userId,
        String profileImage,
        String title,
        String authorName,
        String description,
        String thumbNail,
        Long enrolled,
        String category,
        Integer ratings,
        String language,
        String overview,
        String whatYouWillLearn,
        Integer price,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        Timestamp createdDate,
        boolean isFree,
        boolean isHtmlCourse,
        boolean isPurchased,
        ArrayList<Section> sections,
        ArrayList<Chapter> chapters

) {
}
