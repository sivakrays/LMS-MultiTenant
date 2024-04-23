package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
