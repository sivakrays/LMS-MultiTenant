package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.QuizBean;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Quiz", description = "Quiz management APIs")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/saveBadge")
    public CommonResponse<BadgeCounts> saveBadge(@RequestBody QuizRank quizRank) {
        return quizService.saveBadge(quizRank);
    }

    @PostMapping("/uploadQuizCsv")
    public CommonResponse<List<QuizBean>> uploadQuizCsv(@RequestPart("file") MultipartFile file) throws IOException {
        return quizService.uploadQuizCsv(file);
    }

    @GetMapping(value = "/downloadQuizCsv", produces = "application/octet-stream")
    public CommonResponse<Resource> downloadQuizCsv() throws MalformedURLException {
        return quizService.downloadQuizCsv();
    }

}