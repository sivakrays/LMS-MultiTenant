package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public long userId;

    @Column(nullable = false)
    public String courseId;

    @Column(nullable = false)
    public String sectionId;

    @Column(nullable = false)
    public String subSectionId;

    public long duration;
}
