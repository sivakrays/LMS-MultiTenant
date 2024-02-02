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
public class QuizRank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rank_id")
    private UUID rankId;
    private Long userId;
    private Integer subSectionId;
    private Integer energyPoints;
    private int badge;



}
