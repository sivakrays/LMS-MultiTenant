package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.HtmlCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HtmlCourseRepository extends JpaRepository<HtmlCourse,String> {

    Page<HtmlCourse> findCourseByUserId(Long userId, PageRequest of);

    HtmlCourse findCourseByCourseId(String courseId);

    List<HtmlCourse> findAllByCourseId(String courseId);
}
