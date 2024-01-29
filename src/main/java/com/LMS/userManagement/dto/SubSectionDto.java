package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubSectionDto {
    private Integer subSectionId;
    private Integer sectionId;
    private Integer key;
    private String title;
    private String description;
    private String link;
    private List<QuizDto> quizList;

}
