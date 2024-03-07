package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedCourseDetailDto {

    private Long userId;
    private UUID courseId;
    private Boolean purchased;

}
