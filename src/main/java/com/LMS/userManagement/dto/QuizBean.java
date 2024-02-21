package com.LMS.userManagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizBean {

    public int key;

    public String title;

    public String question;

   public List<String> options;

    public String answer;

}
