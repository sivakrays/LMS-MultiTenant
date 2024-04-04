package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository

public interface SubSectionRepository extends JpaRepository<SubSection, String> {

    @Query(value = "Select * from sub_section where section_id=?1 And sub_section_id=?2",nativeQuery = true)
    Optional<SubSection> findById(@Param("sectionId") Integer sectionId, @Param("subSectionId") Integer subSectionId);
}
