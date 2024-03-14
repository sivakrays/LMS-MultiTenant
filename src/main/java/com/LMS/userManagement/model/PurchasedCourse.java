package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "purchased_course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchasedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private  Long userId;
    private String courseId;
    private Boolean purchased ;
    private Timestamp purchasedOn;

}
