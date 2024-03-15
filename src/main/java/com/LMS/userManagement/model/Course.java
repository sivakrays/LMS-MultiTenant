package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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
    @Column(name = "isFree")
    @JsonProperty("isFree")
    private Boolean isFree;
    private Timestamp date;

    @Column(name = "isHtmlCourse")
    @JsonProperty("isHtmlCourse")
    private Boolean isHtmlCourse;

    @OneToMany(targetEntity = Section.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    private List<Section> sections;

    @OneToMany(targetEntity = Chapter.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "html_course_id",referencedColumnName = "course_id")
    private List<Chapter> chapters;






}
