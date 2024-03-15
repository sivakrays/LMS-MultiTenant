package com.LMS.userManagement.model;

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
    public String id;
    @Column(columnDefinition = "TEXT")
    public String content;
    @Column(columnDefinition = "TEXT")
    public String image;
    public Boolean orderChanged;
    public String type;
}
