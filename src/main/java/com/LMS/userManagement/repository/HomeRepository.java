package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home,Long> {
    Home findByTenantId(String tenantId);
}
