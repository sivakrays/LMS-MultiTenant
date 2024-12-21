package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "purchased_course_sub_section")
public class PurchasedCourseSubSection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchasedCourseSubSectionId;

    private long userId;

    private Long purchasedCourseId;

    private String subSectionId;

    private Boolean isCompleted;

}
