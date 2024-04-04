package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubSectionDto {
    private String subSectionId;
    private String sectionId;
    private Integer key;
    private String title;
    private String description;
    private String link;
    private ArrayList<QuizDto> quizList;

}
