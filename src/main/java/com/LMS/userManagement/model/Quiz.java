package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String quizId;
    private String title;
    private Integer key;
    @Column(columnDefinition = "TEXT")
    private String question;
    private List<String> options;
    private  String answer;


}
