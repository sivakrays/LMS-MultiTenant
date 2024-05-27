package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    private String sectionId;
    private String courseId;
    private Integer key;
    private String title;
    private List<SubSectionDto> subSections;
    private List<QuizDto> quizList;

}
