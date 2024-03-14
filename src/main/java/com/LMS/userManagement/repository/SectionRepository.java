package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SectionRepository extends JpaRepository<Section, String> {

    @Query(value = "Select * from section where section_id=?1 And course_id=?2",nativeQuery = true)
    Optional<Section> findSectionBySectionIdAndCourseId(@Param("sectionId") Integer sectionId,@Param("courseId") Integer courseId);

}
