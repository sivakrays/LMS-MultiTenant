package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long purchasedId;
    @JsonProperty("userId")
    private  long user_id;
    @Column(nullable = false)
    private String courseId;
    private boolean purchased ;
    private Timestamp purchasedOn;

}