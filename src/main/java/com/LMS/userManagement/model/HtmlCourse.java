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
    public String courseId;
    public String chapter;
    public Integer chapterOrder;
    public String content;
    public String image = "";
    public String orderChanged = "false";
    public String type;
}
