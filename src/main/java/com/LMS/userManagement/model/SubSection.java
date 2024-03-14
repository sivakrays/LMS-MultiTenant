package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubSection {
    @Id
    @Column(name = "sub_section_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String subSectionId;
    private Integer key;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String link;

    @OneToMany(targetEntity = Quiz.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_section_id",referencedColumnName = "sub_section_id")
    private List<Quiz> quizList;



}
