package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface SectionRepository extends JpaRepository<Section, String> {

    @Query("SELECT s.sectionId FROM Section s WHERE s.course_id = :courseId")
    List<String> findSectionIdsByCourseId(@Param("courseId") String courseId);

    @Query("SELECT s FROM Section s WHERE s.course_id = :courseId")
    List<Section> findByCourseId(@Param("courseId") String courseId);


}
