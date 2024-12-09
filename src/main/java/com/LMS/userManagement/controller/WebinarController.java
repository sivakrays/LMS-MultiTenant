package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.WebinarRequest;
import com.LMS.userManagement.service.WebinarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/webinars")
public class WebinarController {

    @Autowired
    private WebinarService webinarService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveWebinar(
            @RequestPart("videoFile") MultipartFile videoFile,
            @RequestPart("webinarRequest") String webinarRequestJson
    ) {
        return webinarService.saveWebinar(videoFile, webinarRequestJson);
    }
}
