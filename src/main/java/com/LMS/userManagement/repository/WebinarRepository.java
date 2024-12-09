package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.WebinarMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebinarRepository extends JpaRepository<WebinarMetadata, Long> {
}
