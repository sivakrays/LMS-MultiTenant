package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "course_id")
    public String courseId;
    @Column(nullable = false)
    public long userId;
    public String title;
    public String authorName;
    @Column(columnDefinition = "TEXT")
    public String description;
    @Column(columnDefinition = "TEXT")
    public String thumbNail;
    public long enrolled;
    public String category;
    public int ratings;
    public String language;
    public String overview;
    @Column(columnDefinition = "TEXT")
    public String whatYouWillLearn;
    public int price;
    @Column(name = "isFree")
    @JsonProperty("isFree")
    public boolean isFree;
    @JsonFormat(pattern="yyyy-MM-dd")
    public Timestamp createdDate;

    @Column(name = "isHtmlCourse")
    @JsonProperty("isHtmlCourse")
    public boolean isHtmlCourse;

    @OneToMany(targetEntity = Section.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    public List<Section> sections;

    @OneToMany(targetEntity = Chapter.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "html_course_id",referencedColumnName = "course_id")
    public List<Chapter> chapters;

    public boolean recommended;

    public boolean popular;

}