package com.LMS.userManagement.repository;

import com.LMS.userManagement.dto.EducationContent;
import com.LMS.userManagement.model.EduContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EduContentRepository extends JpaRepository<EduContent,Long> {
    //List<EduContent> findAllByTenantId(String tenantId);

  //  @Query(value = "SELECT IMAGE,IMAGE_CONTENT,IMAGE_TITLE FROM EDU_CONTENT WHERE TENANT_ID=?1",nativeQuery = true)
   // List<EducationContent> findImageByTenantId(String tenantId);
}