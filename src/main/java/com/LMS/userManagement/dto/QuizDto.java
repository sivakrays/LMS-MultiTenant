package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Integer quizId;
    private Integer subSectionId;
    private String title;
    private Integer key;
    private String question;
    private List<String> options;
    private  int answer;
}
