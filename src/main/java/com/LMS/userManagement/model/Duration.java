package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Duration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer durationId;
    private Long userId;
    private UUID courseId;
    private UUID sectionId;
    private UUID subSectionId;
    private Integer playedSeconds;
    private Boolean watched = false;


}
