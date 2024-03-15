package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class
Chapter {

    @Id
    @Column(name = "chapter_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    public String chapterId;
    public Long userId;
    @JsonProperty("courseId")
    public String html_course_id;
    public String chapter;
    public Integer chapterOrder;
    @OneToMany(targetEntity = ChapterContent.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id",referencedColumnName = "chapter_id")
    private List<ChapterContent> chapterContent;
}
