package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "subsectionId"}))
public class QuizRank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rank_id")
    private String rankId;
    private long userId;
    private String sectionId;
    private Integer energyPoints;
    private int badge;



}
