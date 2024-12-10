package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedCompletedCourseDto {

    private long userId;

    private String courseId;

    private LocalDateTime completedDate;

    private Boolean isCompleted;

}
