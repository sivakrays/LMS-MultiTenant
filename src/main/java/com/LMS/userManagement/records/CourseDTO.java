package com.LMS.userManagement.records;

import com.LMS.userManagement.model.Section;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record CourseDTO(
        UUID courseId,
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
        Date date,
        List<Section> sections

) {
}
