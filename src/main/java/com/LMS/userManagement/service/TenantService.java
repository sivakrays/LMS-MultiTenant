package com.LMS.userManagement.service;

import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.repository.TenantRepository;
import jakarta.transaction.Transactional;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    DataSource dataSource;

    public void initDatabase(String schema) {
        Flyway flyway = Flyway.configure()
                .locations("db/migration/tenants")
                .baselineVersion("0")
                .dataSource(dataSource)
                .schemas(schema)
                .load();
        flyway.migrate();
    }

    @Transactional
    public ResponseEntity<?> registerTenant(TenantDetails tenantDetails) {
        Optional<TenantDetails> tenant=tenantRepository.findByTenantId(tenantDetails.getTenantId());
        if(tenant.isEmpty()){
             var tenantDtls=   tenantRepository.save(tenantDetails);
          if (tenantDtls!=null){
              initDatabase(tenantDetails.getTenantId());
              return ResponseEntity.ok("registration success, Tenant-Id::"+tenantDetails.getTenantId());

          }
        }
        return ResponseEntity.ok("registration failed, Tenant-Id already exits");
    }

    public ResponseEntity<?> tenantLogin(String email, String password) {
        Optional<TenantDetails> tenant=tenantRepository.findByEmail(email);
        if (tenant.isPresent() && tenant.get().getPassword().equals(password)){
           return ResponseEntity.ok(tenant.get().getTenantId());
        }
        return ResponseEntity.ok("User not found");
    }

    public ResponseEntity<?> getAllTenants() {
       return ResponseEntity.ok(tenantRepository.findAllIssuer());
       //return ResponseEntity.ok(tenantRepository.findAll());
    }
    public ResponseEntity<?> getTenantByIssuer(String issuer) {
      Optional<TenantDetails> tenant=  tenantRepository.findByIssuer(issuer);
      if (tenant.isPresent()){
         return ResponseEntity.ok(tenant.get().getTenantId());
      }

      return ResponseEntity.ok("Tenant cannot be found");
    }
}
