package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.TenantDto;
import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.repository.TenantRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import jakarta.transaction.Transactional;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
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
    public CommonResponse<List<TenantDetails>> registerTenant(TenantDto tenantDetails) throws Exception {
        String tenantId = tenantDetails.getTenantId()
                .replaceAll(" ", "_")
                .toLowerCase();

        String email = tenantDetails.getEmail();
        Optional<TenantDetails> tenantByTenantId = tenantRepository.findByTenantId(tenantId);
        Optional<TenantDetails> tenantByEmail = tenantRepository.findByEmail(email);
        if (tenantByTenantId.isEmpty() && tenantByEmail.isEmpty()) {

            var tenantDtls = TenantDetails.builder()
                    .tenantId(tenantId)
                    .role("manager")
                    .issuer(tenantDetails.getIssuer())
                    .email(tenantDetails.getEmail())
                    .password(tenantDetails.getPassword())
                    .createdDate(new Timestamp(System.currentTimeMillis()))
                    .build();
            var savedTenant = tenantRepository.save(tenantDtls);
            try {
                initDatabase(savedTenant.getTenantId());
            } catch (Exception e) {
                throw new Exception("failed to initialize database for new tenant ::::::" + e.getMessage());
            }
            List<TenantDetails> tenantList = tenantRepository.findAll();
              /*TenantDto  t = TenantDto.builder()
                        .role(savedTenant.getRole())
                        .issuer(savedTenant.getIssuer())
                        .tenantId(savedTenant.getTenantId())
                        .email(savedTenant.getEmail())
                        .build();*/

            return CommonResponse.<List<TenantDetails>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.TENANT_REGISTERED_SUCCESSFUL)
                    .data(tenantList)
                    .build();
        }
        List<TenantDetails> tenantList = tenantRepository.findAll();
        return CommonResponse.<List<TenantDetails>>builder()
                .status(false)
                .statusCode(Constant.FORBIDDEN)
                .message(Constant.TENANT_EXISTS)
                .data(tenantList)
                .build();

    }


    public CommonResponse<TenantDto> tenantLogin(LoginDTO loginDto) {
        try {
            String email = loginDto.email();
            String password = loginDto.password();
            Optional<TenantDetails> tenant = tenantRepository.findByEmail(email);
            if (tenant.isPresent() && tenant.get().getPassword().equals(password)) {
                var t = tenant.get();
                TenantDto tenantDto = TenantDto.builder()
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
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<TenantDto>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.LOGIN_FAILED)
                    .build();
        }
    }

}