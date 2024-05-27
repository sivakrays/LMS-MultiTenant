package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.AdminDto;
import com.LMS.userManagement.model.Admin;
import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.repository.AdminRepository;
import com.LMS.userManagement.repository.TenantRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final TenantRepository tenantRepository;

    private final TenantService tenantService;

    @PersistenceContext
    private EntityManager entityManager;


    public CommonResponse<Admin> adminRegistration(AdminDto adminDto) {
        try {
            var adminDetails = adminRepository.findByEmail(adminDto.getEmail());
            if (adminDetails.isPresent()) {
                return CommonResponse.<Admin>builder()
                        .status(false)
                        .message(Constant.USER_EXISTS)
                        .statusCode(Constant.FORBIDDEN)
                        .build();
            }

            var admin = Admin.builder()
                    .role("owner")
                    .password(adminDto.getPassword()) // Avoid exposing password in response
                    .createdDate(new Timestamp(System.currentTimeMillis()))
                    .email(adminDto.getEmail())
                    .build();
            Admin   savedAdmin = adminRepository.save(admin);

            return CommonResponse.<Admin>builder()
                    .status(true)
                    .message(Constant.ADMIN_REGISTERED)
                    .statusCode(Constant.SUCCESS)
                    .data(savedAdmin) // Assuming you want to return admin ID
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<Admin>builder()
                    .status(false)
                    .statusCode(Constant.FORBIDDEN)
                    .error(e.getMessage())
                    .build();
        }
    }


    public CommonResponse<AdminDto> adminLogin(LoginDTO loginDto) {
        try {
            String email = loginDto.email();
            String password = loginDto.password();
            Optional<Admin> admin = adminRepository.findByEmail(email);

            if (admin.isPresent() && admin.get().getPassword().equals(password)) {
                var ad = admin.get();
                AdminDto  adminDto = AdminDto.builder()
                        .email(ad.getEmail())
                        .role(ad.getRole())
                        .build();
                return CommonResponse.<AdminDto>builder()
                        .status(true)
                        .message(Constant.LOGIN_SUCCESS)
                        .statusCode(Constant.SUCCESS)
                        .data(adminDto)
                        .build();
            } else {
                return CommonResponse.<AdminDto>builder()
                        .status(false)
                        .message(Constant.USER_EXISTS)
                        .statusCode(Constant.FORBIDDEN)
                        .build();
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<AdminDto>builder()
                    .status(false)
                    .statusCode(Constant.FORBIDDEN)
                    .error(e.getMessage())
                    .build();
        }
    }


    @Transactional
    public CommonResponse<List<TenantDetails>> deleteTenant(long id) {
        List<TenantDetails> tenantList =tenantRepository.findAll();
                try {

            Optional<TenantDetails>    tenant = tenantRepository.findById(id);
            if (tenant.isEmpty()) {
                return CommonResponse.<List<TenantDetails>>builder()
                        .status(false)
                        .statusCode(Constant.SUCCESS)
                        .data(tenantList)
                        .build();
            }
            var tenantDtls = tenant.get();
            String schemaName = tenantDtls.getTenantId();
            entityManager.createNativeQuery("DROP SCHEMA IF EXISTS " + schemaName + " CASCADE").executeUpdate();
            tenantRepository.deleteById(id);
            tenantList=tenantRepository.findAll();
            return CommonResponse.<List<TenantDetails>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.REMOVED_USER)
                    .data(tenantList)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<List<TenantDetails>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_DELETE_TENANT)
                    .data(tenantList)
                    .build();
        }
    }


    public CommonResponse<Map<String, String>> getAllTenants() {
        Map<String, String> tenantIdMap  = new HashMap<>();
        List<TenantDetails> tenantList = tenantRepository.findAll();
        if (tenantList.isEmpty()) {
            return CommonResponse.<Map<String, String>>builder()
                    .status(false)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.NO_DATA)
                    .data(tenantIdMap)
                    .build();
        }

        tenantList.forEach(n -> {
            tenantIdMap.put(n.getIssuer(), n.getTenantId());
        });

        return CommonResponse.<Map<String, String>>builder()
                .status(true)
                .statusCode(Constant.SUCCESS)
                .message(Constant.DATA_FOUND)
                .data(tenantIdMap)
                .build();

    }



    public CommonResponse<List<TenantDetails>> findAllTenants() {
        List<TenantDetails> tenantList = null;
        try {
            tenantList = tenantRepository.findAll();
            if (tenantList.isEmpty()) {
                return CommonResponse.<List<TenantDetails>>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.NO_TENANTS)
                        .data(tenantList)
                        .build();
            }
            return CommonResponse.<List<TenantDetails>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.TENANTS_FOUND)
                    .data(tenantList)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<List<TenantDetails>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_TENANT)
                    .data(tenantList)
                    .build();
        }
    }


    @Transactional
    public CommonResponse<Optional<TenantDetails>> updateSchemaByTenant(String email) {
        Optional<TenantDetails> tenantDetails = null;
        try {
            tenantDetails = tenantRepository.findByEmail(email);
            if (tenantDetails.isEmpty()) {
                return CommonResponse.<Optional<TenantDetails>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.TENANT_NOT_FOUND_BY_EMAIL)
                        .data(null)
                        .build();
            }
            tenantService.initDatabase(tenantDetails.get().getTenantId());
            return CommonResponse.<Optional<TenantDetails>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.SCHEMA_UPDATED)
                    .data(tenantDetails)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<Optional<TenantDetails>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.SCHEMA_UPDATE_FAILED)
                    .data(tenantDetails)
                    .build();
        }
    }

}