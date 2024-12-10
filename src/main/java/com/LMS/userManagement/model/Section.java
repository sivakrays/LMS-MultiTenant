package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "section_id")
    private String sectionId;

    @JsonProperty(value = "courseId")
    private String course_id;

    private Integer key;

    private String title;

    @OneToMany(targetEntity = SubSection.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id",referencedColumnName = "section_id")
    private List<SubSection> subSections;

    @OneToMany(targetEntity = Quiz.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id",referencedColumnName = "section_id")
    private List<Quiz> quizList;

}
