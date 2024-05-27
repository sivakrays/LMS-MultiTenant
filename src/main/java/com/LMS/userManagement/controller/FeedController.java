package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.Feedback;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.FeedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }


    @PostMapping("/saveFeedback")
    public CommonResponse<Feedback> saveFeedback(@RequestBody Feedback feedback){
        return feedService.saveFeedback(feedback);

    }

}
