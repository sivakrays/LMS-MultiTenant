package com.LMS.userManagement.repository;

import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.model.PurchasedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchasedCourseRepository extends JpaRepository<PurchasedCourse, Long> {

    List<PurchasedCourse> findByUserId(Long userId);

    @Query(value = "SELECT pc.courseId FROM PurchasedCourse pc WHERE pc.userId = :userId")
    List<String> findCourseIdsByUserId(Long userId);

    @Query(value = "SELECT PURCHASED FROM PURCHASED_COURSE WHERE COURSE_ID=?1 AND USER_ID=?2 AND PURCHASED = true",nativeQuery = true)
    Boolean findByCourseIdAndUserId(String courseId, Long userId);

    @Query(value = "SELECT pc.user_id,pc.course_id,c.title,c.is_html_course,c.is_free,c.thumb_nail,c.ratings,c.price,c.language,c.created_date,c.category,c.author_name," +
            " COALESCE(pc.purchased, false) AS purchased FROM purchased_course pc INNER JOIN course c ON pc.course_id = c.course_id WHERE pc.user_id = ?1",nativeQuery = true)
    List<CourseDetailDto> findPurchasedCourseByUserId(Long userId);

    PurchasedCourse findByUserIdAndCourseId(Long userId,String courseId);

    @Query("SELECT p FROM PurchasedCourse p WHERE p.userId = :userId AND p.purchasedId = :purchasedId")
    Optional<PurchasedCourse> findByUserIdAndPurchasedId(@Param("userId") long userId, @Param("purchasedId") Long purchasedId);


    Boolean existsByUserId(Long userId);

    @Query(value = "SELECT course_id FROM purchased_course WHERE user_id IN :userIds AND is_completed = true", nativeQuery = true)
    List<String> findCompletedCoursesByUserIds(@Param("userIds") List<Long> userIds);

    @Query(value = "SELECT course_id FROM purchased_course WHERE user_id = :userId AND is_completed = true", nativeQuery = true)
    List<String> findCompletedCoursesByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT course_id FROM purchased_course WHERE user_id IN :userIds AND is_completed = true AND completed_date BETWEEN CURRENT_DATE - INTERVAL :daysCount DAY AND CURRENT_DATE", nativeQuery = true)
    List<String> findCompletedCoursesByUserIdsAndDays(@Param("userIds") List<Long> userIds, @Param("daysCount") Integer daysCount);

    @Query("SELECT p.purchasedId FROM PurchasedCourse p WHERE p.userId = :userId AND p.courseId = :courseId")
    Optional<Long> findPurchasedIdByUserIdAndCourseId(@Param("userId") long userId, @Param("courseId") String courseId);


}
