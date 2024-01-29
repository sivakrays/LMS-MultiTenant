package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rank_id")
    private Integer rankId;
    private Long userId;
    private Integer subSectionId;
    private Integer energyPoints;
    private int badge;



}
