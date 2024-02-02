package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "section_id")
    private UUID sectionId;
    private Integer course_id;
    private Integer key;
    private String title;
    @OneToMany(targetEntity = SubSection.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id",referencedColumnName = "section_id")
    private List<SubSection> subSections;



}
