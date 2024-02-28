package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DurationRepository extends JpaRepository<Duration,Integer>{



}
