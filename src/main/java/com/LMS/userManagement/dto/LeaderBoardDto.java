package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoardDto {

    private String username;
    private Long userId;
    private Integer rank;
    private String profileImage;
    private Integer goldCount;
    private Integer silverCount;
    private Integer bronzeCount;
    private Integer energyPoints;
    private Integer completedCourseCount;

}
