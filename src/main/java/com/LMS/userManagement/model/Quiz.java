package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID quizId;
    private String title;
    private Integer key;
    @Column(columnDefinition = "TEXT")
    private String question;
    private List<String> options;
    @Column(columnDefinition = "int default 0")
    private int watched;
    private  int answer;


}
