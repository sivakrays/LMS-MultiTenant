package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.HtmlCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HtmlCourseRepository extends JpaRepository<HtmlCourse,String> {

    Page<HtmlCourse> findByUserId(Long userId, PageRequest of);

//    @Query(value = "SELECT * FROM HTML_COURSE WHERE HTML_COURSE_ID=?1",nativeQuery = true)
//    HtmlCourse findByCourseId(String courseId);

    @Query(value = "SELECT * FROM HTML_COURSE WHERE HTML_COURSE_ID=?1",nativeQuery = true)
    List<HtmlCourse> findAllByCourseId(String courseId);
}
