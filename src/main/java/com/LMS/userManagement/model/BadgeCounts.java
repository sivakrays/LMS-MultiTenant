package com.LMS.userManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeCounts {
    private Long userId;
    private int gold;
    private int silver;
    private int bronze;
    private Integer energyPoints;



}
