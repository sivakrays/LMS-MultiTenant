package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "webinar_metadata")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WebinarMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meeting_id")
    private String meetingId;

    @Column(name = "recorded_by")
    private String recordedBy;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "video_format")
    private String videoFormat;

    @Column(name = "screen_sharing")
    private boolean screenSharing;

    @Column(name = "file_path")
    private String filePath;
}
