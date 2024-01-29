package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.TenantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<TenantDetails,Long> {
    Optional<TenantDetails> findByTenantId(String tenantId);

    Optional<TenantDetails> findByEmail(String email);

    @Query(value = "SELECT issuer FROM tenant_details",nativeQuery = true)
    List<String> findAllIssuer();

    Optional<TenantDetails> findByIssuer(String issuer);
}
