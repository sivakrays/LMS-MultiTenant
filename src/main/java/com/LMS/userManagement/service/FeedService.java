package com.LMS.userManagement.service;

import com.LMS.userManagement.model.Feedback;
import com.LMS.userManagement.repository.FeedbackRepository;
import com.LMS.userManagement.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FeedService {

    private final FeedbackRepository feedbackRepository;

    public FeedService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public CommonResponse<Feedback> saveFeedback(Feedback feedback) {
        feedback.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Feedback feed = feedbackRepository.save(feedback);
        return CommonResponse.<Feedback>builder()
                .status(true)
                .data(feed)
                .message("Thanks for your feedback")
                .statusCode(200)
                .build();
    }

}
