package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Tag(name = "Quiz", description = "Quiz management APIs")

public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/saveBadge")
    public ResponseEntity<?> saveBadge(@RequestBody QuizRank quizRank) {
        return quizService.saveBadge(quizRank);
    }


    @PostMapping("/uploadQuizCsv")
    public ResponseEntity<?> uploadQuizCsv(@RequestPart("file") MultipartFile file) throws IOException {
        return quizService.uploadQuizCsv(file);
    }
}
