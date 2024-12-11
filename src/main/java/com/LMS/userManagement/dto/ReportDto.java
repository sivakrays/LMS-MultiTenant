package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Integer purchasedCourseCount;
    private Integer completedCourseCount;
    private Integer inCompletedCourseCount;
    private String username;
    private long userId;
    private Integer goldBadgeCount;
    private Integer silverBadgeCount;
    private Integer bronzeBadgeCount;

}
