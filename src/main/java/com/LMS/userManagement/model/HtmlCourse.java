package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class
HtmlCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public Long userId;
    @JsonProperty("courseId")
    public String html_course_id;
    public String chapter;
    public Integer chapterOrder;
    @Column(columnDefinition = "TEXT")
    public String content;
    @Column(columnDefinition = "TEXT")
    public String image;
    public Boolean orderChanged;
    public String type;
}
