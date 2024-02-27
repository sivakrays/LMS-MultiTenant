package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.TenantDto;
import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.repository.TenantRepository;
import jakarta.transaction.Transactional;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    AuthService authService;

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
<<<<<<< HEAD
    public ResponseEntity<?> registerTenant(TenantDto tenantDetails) {
        Optional<TenantDetails> tenant=tenantRepository.findByTenantId(tenantDetails.getTenantId());
        if(tenant.isEmpty()){
      var tenantDtls=      TenantDetails.builder()
                    .tenantId(tenantDetails.getTenantId())
                    .role("manager")
                    .issuer(tenantDetails.getIssuer())
                    .email(tenantDetails.getEmail())
                    .password(tenantDetails.getPassword())
              .createdDate(new Timestamp(System.currentTimeMillis())).build();
             var savedTenant=   tenantRepository.save(tenantDtls);
          if (savedTenant!=null){
              initDatabase(tenantDetails.getTenantId());
           var t=   TenantDto.builder()
                      .role(savedTenant.getRole())
                      .issuer(savedTenant.getIssuer())
                      .tenantId(savedTenant.getTenantId())
                      .email(savedTenant.getEmail()).build();

              return ResponseEntity.status(HttpStatus.OK).body(t);

          }
=======
    public CommonResponse<TenantDto> registerTenant(TenantDto tenantDetails) {
        TenantDto t = null;
        try {
            Optional<TenantDetails> tenant = tenantRepository.findByTenantId(tenantDetails.getTenantId());
            if (tenant.isEmpty()) {
                var tenantDtls = TenantDetails.builder()
                        .tenantId(tenantDetails.getTenantId())
                        .role("manager")
                        .issuer(tenantDetails.getIssuer())
                        .email(tenantDetails.getEmail())
                        .password(tenantDetails.getPassword())
                        .createdDate(new Timestamp(System.currentTimeMillis()))
                        .build();
                var savedTenant = tenantRepository.save(tenantDtls);
                if (savedTenant != null) {
                    initDatabase(tenantDetails.getTenantId());
                    t = TenantDto.builder()
                            .role(savedTenant.getRole())
                            .issuer(savedTenant.getIssuer())
                            .tenantId(savedTenant.getTenantId())
                            .email(savedTenant.getEmail())
                            .build();

                    return CommonResponse.<TenantDto>builder()
                            .status(true)
                            .statusCode(Constant.SUCCESS)
                            .message(Constant.TENANT_REGISTERED_SUCCESSFUL)
                            .data(t)
                            .build();
                }
            }
            return CommonResponse.<TenantDto>builder()
                    .status(false)
                    .statusCode(Constant.FORBIDDEN)
                    .message(Constant.TENANT_EXISTS)
                    .data(t)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<TenantDto>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_REGISTER_TENANT)
                    .data(t)
                    .build();
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant already exists");
    }

<<<<<<< HEAD
    public ResponseEntity<?> tenantLogin(LoginDTO loginDto) {
        String email = loginDto.email();
        String password = loginDto.password();
        Optional<TenantDetails> tenant=tenantRepository.findByEmail(email);
        if (tenant.isPresent() && tenant.get().getPassword().equals(password)){
          var t=  tenant.get();
         var tenantDto =  TenantDto.builder()
                    .email(t.getEmail())
                    .role(t.getRole())
                    .issuer(t.getIssuer())
                    .tenantId(t.getTenantId())
=======

    public CommonResponse<TenantDto> tenantLogin(LoginDTO loginDto) {
        TenantDto tenantDto = null;
        try {
            String email = loginDto.email();
            String password = loginDto.password();
            Optional<TenantDetails> tenant = tenantRepository.findByEmail(email);
            if (tenant.isPresent() && tenant.get().getPassword().equals(password)) {
                var t = tenant.get();
                tenantDto = TenantDto.builder()
                        .email(t.getEmail())
                        .role(t.getRole())
                        .issuer(t.getIssuer())
                        .tenantId(t.getTenantId())
                        .build();

                return CommonResponse.<TenantDto>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.LOGIN_SUCCESS)
                        .data(tenantDto)
                        .build();
            }
            return CommonResponse.<TenantDto>builder()
                    .status(false)
                    .statusCode(Constant.UNAUTHORIZED)
                    .message(Constant.TENANT_NOT_FOUND)
                    .data(tenantDto)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<TenantDto>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.LOGIN_FAILED)
                    .data(tenantDto)
>>>>>>> d3a4e0276580c6bff977241ede174a99b09b7795
                    .build();


           return ResponseEntity.status(HttpStatus.OK).body(tenantDto);
        }
        return ResponseEntity.status(403).body("user not found");
    }



}
