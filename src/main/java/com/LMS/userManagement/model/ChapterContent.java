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
public class ChapterContent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "chapter_content_id")
    public String chapterContentId;
    public Integer chapterContentOrder;
    @Column(columnDefinition = "TEXT")
    public String content;
    @Column(columnDefinition = "TEXT")
    public String image;
    public Boolean orderChanged;
    public String type;
}