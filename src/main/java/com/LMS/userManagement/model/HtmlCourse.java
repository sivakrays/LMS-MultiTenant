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
public class
HtmlCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public Long userId;
    public String courseId;
    public String chapter;
    public String content;
    public String image = "false";
    public String orderChanged = "false";
    public String type;
}
