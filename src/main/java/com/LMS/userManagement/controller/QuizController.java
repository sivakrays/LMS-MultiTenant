package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.QuizBean;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Tag(name = "Quiz", description = "Quiz management APIs")

public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/saveBadge")
<<<<<<< HEAD
    public ResponseEntity<?> saveBadge(@RequestBody QuizRank quizRank) {
=======
    public CommonResponse<BadgeCounts> saveBadge(@RequestBody QuizRank quizRank) {
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
        return quizService.saveBadge(quizRank);
    }


    @PostMapping("/uploadQuizCsv")
<<<<<<< HEAD
    public ResponseEntity<?> uploadQuizCsv(@RequestPart("file") MultipartFile file) throws IOException {
=======
    public CommonResponse<List<QuizBean>> uploadQuizCsv(@RequestPart("file") MultipartFile file) throws IOException {
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
        return quizService.uploadQuizCsv(file);
    }

    @GetMapping(value = "/downloadQuizCsv",produces ="application/octet-stream")
<<<<<<< HEAD
    public ResponseEntity<?> downloadQuizCsv() throws MalformedURLException {
=======
    public CommonResponse<Resource> downloadQuizCsv() throws MalformedURLException {
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
        return quizService.downloadQuizCsv();
    }
}
