package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HtmlCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    public UUID courseId;

    public Long userId;

    @Column(columnDefinition = "TEXT")
    public String courseContent;

    public String type;

    public Boolean isHtmlContent;
}
