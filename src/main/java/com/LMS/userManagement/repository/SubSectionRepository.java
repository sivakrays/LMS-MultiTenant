package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository

public interface SubSectionRepository extends JpaRepository<SubSection, String> {

    @Query(value = "SELECT * FROM sub_section WHERE section_id = ?1 AND sub_section_id = ?2", nativeQuery = true)
    Optional<SubSection> findById(@Param("sectionId") String sectionId, @Param("subSectionId") String subSectionId);

//    @Query("SELECT ss.subSectionId FROM SubSection ss WHERE ss.section.sectionId IN :sectionIds")
//    List<String> findSubSectionIdsBySectionIds(@Param("sectionIds") List<String> sectionIds);


}
