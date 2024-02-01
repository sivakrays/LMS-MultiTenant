package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/saveBadge")
    public ResponseEntity<?> saveBadge(@RequestBody QuizRank quizRank) {
        return quizService.saveBadge(quizRank);
    }

}
