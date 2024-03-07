package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.PurchasedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchasedCourseRepository extends JpaRepository<PurchasedCourse, Long> {
    List<PurchasedCourse> findByUserId(Long userId);
    @Query("SELECT pc.courseId FROM PurchasedCourse pc WHERE pc.userId = :userId")
    List<UUID> findCourseIdsByUserId(Long userId);
}
