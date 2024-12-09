package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.WebinarRequest;
import com.LMS.userManagement.model.WebinarMetadata;
import com.LMS.userManagement.repository.WebinarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class WebinarService {

    private static final Logger logger = LoggerFactory.getLogger(WebinarService.class);
    private static final String UPLOAD_DIR = "C:/webinar/uploads/";

    @Autowired
    private WebinarRepository webinarRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<Object> saveWebinar(MultipartFile videoFile, String webinarRequestJson) {
        try {
            // Parse webinarRequestJson to WebinarRequest DTO
            WebinarRequest webinarRequest = objectMapper.readValue(webinarRequestJson, WebinarRequest.class);

            // Validate video file
            if (videoFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Video file is required.");
            }
            String originalFileName = videoFile.getOriginalFilename();
            if (originalFileName == null || !isValidFileType(originalFileName)) {
                return ResponseEntity.badRequest().body("Invalid file type. Allowed: .mp4, .mov.");
            }

            // Generate unique filename
            String uniqueFileName = UUID.randomUUID() + "-" + originalFileName;
            Path uploadPath = Paths.get(UPLOAD_DIR, uniqueFileName);

            // Create directory if it doesn't exist
            Files.createDirectories(uploadPath.getParent());

            // Save the video file
            Files.write(uploadPath, videoFile.getBytes());

            // Save metadata to the database
            WebinarMetadata webinarMetadata = WebinarMetadata.builder()
                    .meetingId(webinarRequest.getMeetingId())
                    .recordedBy(webinarRequest.getRecordedBy())
                    .startTime(webinarRequest.getStartTime())
                    .endTime(webinarRequest.getEndTime())
                    .videoFormat(webinarRequest.getVideoFormat())
                    .screenSharing(webinarRequest.isScreenSharing())
                    .filePath(uploadPath.toString())
                    .build();

            webinarRepository.save(webinarMetadata);

            logger.info("Webinar saved successfully: {}", webinarMetadata);
            return ResponseEntity.ok().body("Webinar saved successfully.");
        } catch (Exception e) {
            logger.error("Error saving webinar", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save webinar.");
        }
    }

    private boolean isValidFileType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        return fileExtension.equals(".mp4") || fileExtension.equals(".mov");
    }
}
