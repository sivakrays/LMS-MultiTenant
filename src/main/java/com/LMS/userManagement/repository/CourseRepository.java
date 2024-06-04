package com.LMS.userManagement.repository;

import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    Course findByCourseId(String courseId);


    @Query(value = "SELECT c.user_id,c.course_id,c.title,c.is_html_course,c.thumb_nail,c.ratings,c.price,c.language,c.created_date,c.category,c.author_name FROM course c WHERE " +
            "c.title iLIKE CONCAT('%',:search, '%')" +
            "OR c.description iLIKE CONCAT('%', :search, '%') " +
            "OR c.category iLIKE CONCAT ('%', :search, '%')",nativeQuery = true)
    List<CourseDetailDto> searchAllCourse( @Param("search")String search);

   List<Course> findByUserId(Long userId);


    @Query(value = "SELECT PROFILE_IMAGE FROM USER_DETAILS WHERE ID=?1", nativeQuery = true)
     String findUserProfileByUserId(Long userId) ;

    //to get specific detail ,query wrote by siva ,no parameter passed in this query
    @Query(value = "SELECT u.profile_image,c.user_id,c.course_id,c.title,c.is_html_course,c.is_free,c.thumb_nail,c.ratings,c.price,c.language,c.created_date,c.category,c.author_name FROM course c  JOIN user_details u ON  u.id=c.user_id",nativeQuery = true)
   List<CourseDetailDto> findAllCourseDetails();



    @Query(value = "SELECT c.user_id,c.course_id,c.title,c.is_html_course,c.is_free,c.thumb_nail,c.ratings,c.price,c.language,c.created_date," +
            "c.category,c.author_name,CASE WHEN EXISTS (SELECT pc.purchased FROM purchased_course pc WHERE pc.course_id = c.course_id " +
            "AND pc.user_id = c.user_id) THEN true ELSE false END AS purchased FROM course c  WHERE c.user_id = ?1",nativeQuery = true)
    List<CourseDetailDto> findAllCourseDetailsByUserId(Long userId);

    @Query(value = "SELECT user_id,course_id,title,is_html_course,is_free,thumb_nail,ratings,price,language,created_date,category,author_name FROM course WHERE course_id IN :courseId",nativeQuery = true)
    List<CourseDetailDto> findAllCourseDetailsById(List<String> courseId);

    @Query(value = "SELECT c.user_id,c.course_id,c.title,c.is_html_course,c.is_free,c.thumb_nail,c.ratings,c.price,c.language,c.created_date,c.category," +
            "c.author_name,COALESCE(p.purchased, false) AS purchased FROM course c LEFT JOIN purchased_course p ON c.course_id = p.course_id "+
            "WHERE(p.course_id IS NULL OR p.purchased = false OR p.purchased IS NULL) AND c.user_id=?1",nativeQuery = true)
    List<CourseDetailDto> findAllCourseDetailByUserId(Long userId);

    @Query(value = "SELECT c.user_id,c.course_id,c.title,c.is_html_course,c.is_free,c.thumb_nail,c.ratings,c.price,c.language,c.created_date,c.category,c.author_name FROM course c   WHERE c.user_id=?1",nativeQuery = true)
    List<CourseDetailDto> findCourseByUserId(Long userId);



    @Query(value="SELECT u.profile_image,c.user_id,c.course_id,c.title,c.is_html_course,c.is_free,c.thumb_nail,c.ratings,c.price,c.language,c.created_date,c.category,c.author_name FROM course c  JOIN user_details u ON  u.id=c.user_id WHERE c.visible_to = :visibleTo",nativeQuery = true)
    List<CourseDetailDto> findCoursesVisibleTo(@Param("visibleTo") String classroomId);

    @Query(value="SELECT u.profile_image,c.user_id,c.course_id,c.title,c.is_html_course,c.is_free,c.thumb_nail,c.ratings,c.price,c.language,c.created_date,c.category,c.author_name FROM course c  JOIN user_details u ON  u.id=c.user_id WHERE c.visible_to ='public'",nativeQuery = true)
    List<CourseDetailDto> findCoursesVisibleToPublic();


    boolean existsByVisibleTo(String classRoomId);







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
