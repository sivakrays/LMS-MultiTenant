package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.PurchasedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedCourseRepository extends JpaRepository<PurchasedCourse, Long> {
    List<PurchasedCourse> findByUserId(Long userId);
    @Query(value = "SELECT pc.courseId FROM PurchasedCourse pc WHERE pc.userId = :userId")
    List<String> findCourseIdsByUserId(Long userId);

    @Query(value = "SELECT PURCHASED FROM PURCHASED_COURSE WHERE COURSE_ID=?1 AND USER_ID=?2 AND PURCHASED=true",nativeQuery = true)
    Boolean findByCourseIdAndUserId(String courseId, Long userId);

}
