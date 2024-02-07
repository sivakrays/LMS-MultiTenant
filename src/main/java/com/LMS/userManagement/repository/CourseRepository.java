package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Course findCourseByCourseId(UUID courseId);
    @Query(value = "SELECT * FROM course c WHERE " +
            "c.title iLIKE CONCAT('%',:search, '%')" +
            "OR c.description iLIKE CONCAT('%', :search, '%') " +
            "OR c.category iLIKE CONCAT ('%', :search, '%')",nativeQuery = true)
    List<Course> searchAllCourse( @Param("search")String search);

   Page<Course> findCourseByUserId(Long userId, PageRequest of);







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
