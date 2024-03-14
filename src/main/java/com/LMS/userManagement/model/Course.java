package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "course_id")
    private String courseId;
    @Column(nullable = false)
    private Long userId;
    private String title;
    private String authorName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String thumbNail;
    private Long enrolled;
    private String category;
    private Integer ratings;
    private String language;
    private String overview;
    @Column(columnDefinition = "TEXT")
    private String whatYouWillLearn;
    private Integer price;
    private Date date;
    private Boolean isHtmlCourse;
    @OneToMany(targetEntity = Section.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    private List<Section> sections;

}
