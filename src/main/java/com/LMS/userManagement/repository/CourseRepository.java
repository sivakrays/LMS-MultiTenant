package com.LMS.userManagement.repository;

import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    Course findCourseByCourseId(String courseId);
    @Query(value = "SELECT c.user_id,c.course_id,c.title,c.is_html_course,c.thumb_nail,c.ratings,c.price,c.language,c.date,c.category,c.author_name FROM course c WHERE " +
            "c.title iLIKE CONCAT('%',:search, '%')" +
            "OR c.description iLIKE CONCAT('%', :search, '%') " +
            "OR c.category iLIKE CONCAT ('%', :search, '%')",nativeQuery = true)
    List<CourseDetailDto> searchAllCourse( @Param("search")String search);

   Page<Course> findCourseByUserId(Long userId, PageRequest of);


   @Query(value = "SELECT PROFILE_IMAGE FROM USER_DETAILS WHERE ID=?1",nativeQuery = true)
   String findUserProfileByUserId(Long userId);

   @Query(value = "SELECT user_id,course_id,title,is_html_course,thumb_nail,ratings,price,language,date,category,author_name FROM course",nativeQuery = true)
   List<CourseDetailDto> findAllCourseDetails();







/*
    @Query(value = "SELECT * FROM course c " +
            "WHERE (:title IS NULL OR LOWER(c.title) LIKE CONCAT('%', LOWER(CAST(:title AS TEXT)), '%')) " +
            "AND (:description IS NULL OR LOWER(c.description) LIKE CONCAT('%', LOWER(CAST(:description AS TEXT)), '%')) " +
            "AND (:category IS NULL OR LOWER(c.category) LIKE CONCAT('%', LOWER(CAST(:category AS TEXT)), '%'))",
            nativeQuery = true)
    List<Course> findCoursesByTitleDescriptionCategory(
            @Param("title") String title,
            @Param("description") String description,
            @Param("category") String category);

*/

}
