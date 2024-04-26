package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.CourseTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTrackRepository extends JpaRepository<CourseTracker,Long> {


    List<CourseTracker> findByUserIdAndCourseId(Long userId, String courseId);
}
