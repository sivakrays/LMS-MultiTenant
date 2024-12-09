package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebinarRequest {
    private String meetingId;
    private String recordedBy;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String videoFormat;
    private boolean screenSharing;
    private String fileName; // Simulate the video file as a name/path
}

