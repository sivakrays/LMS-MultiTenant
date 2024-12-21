package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubSection {

    @Id
    @Column(name = "sub_section_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String subSectionId;

    private Integer key;

    private String title;

    private String courseId;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String link;

}
