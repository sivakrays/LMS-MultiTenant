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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final TenantRepository tenantRepository;

    private final TenantService tenantService;

    @PersistenceContext
    private EntityManager entityManager;


    public CommonResponse<Admin> adminRegistration(AdminDto adminDto) {
        Admin savedAdmin = null;
        try {
            var adminDetails = adminRepository.findAllByEmail(adminDto.getEmail());
            if (adminDetails.isPresent()) {
                return CommonResponse.<Admin>builder()
                        .status(false)
                        .message(Constant.USER_EXISTS)
                        .statusCode(Constant.FORBIDDEN)
                        .data(savedAdmin)
                        .build();
            }

            var admin = Admin.builder()
                    .role("owner")
                    .password(adminDto.getPassword()) // Avoid exposing password in response
                    .createdDate(new Timestamp(System.currentTimeMillis()))
                    .email(adminDto.getEmail())
                    .build();
            savedAdmin = adminRepository.save(admin);

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
                    .message(Constant.FAILED_ADMIN_REGISTER)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .data(savedAdmin)
                    .build();
        }
    }


    public CommonResponse<AdminDto> adminLogin(LoginDTO loginDto) {
        AdminDto adminDto = null;
        try {
            String email = loginDto.email();
            String password = loginDto.password();
            Optional<Admin> admin = adminRepository.findAllByEmail(email);

            if (admin.isPresent() && admin.get().getPassword().equals(password)) {
                var ad = admin.get();
                adminDto = AdminDto.builder()
                        .password(null)
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
                        .data(adminDto)
                        .build();
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<AdminDto>builder()
                    .status(false)
                    .message(Constant.LOGIN_FAILED)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .data(adminDto)
                    .build();
        }
    }


    @Transactional
    public CommonResponse<Optional<TenantDetails>> deleteTenant(long id) {
        Optional<TenantDetails> tenant = null;
        try {
            tenant = tenantRepository.findById(id);
            if (tenant.isEmpty()) {
                return CommonResponse.<Optional<TenantDetails>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.NO_TENANTS)
                        .data(null)
                        .build();
            }
            var tenantDtls = tenant.get();
            String schemaName = tenantDtls.getTenantId();
            entityManager.createNativeQuery("DROP SCHEMA IF EXISTS " + schemaName + " CASCADE").executeUpdate();
            tenantRepository.deleteById(id);
            return CommonResponse.<Optional<TenantDetails>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.REMOVED_USER)
                    .data(null)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<Optional<TenantDetails>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_DELETE_TENANT)
                    .data(null)
                    .build();
        }
    }


    public CommonResponse<Map<String, String>> getAllTenants() {
        Map<String, String> tenantIdMap  = new HashMap<>();
        List<TenantDetails> tenantList;
        try {
            tenantList = tenantRepository.findAll();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<Map<String, String>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_TENANT)
                    .data(tenantIdMap)
                    .build();
        }
        if (tenantList.isEmpty()) {
            return CommonResponse.<Map<String, String>>builder()
                    .status(false)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.NO_TENANTS)
                    .data(tenantIdMap)
                    .build();
        }

        tenantList.forEach(n -> {
            tenantIdMap.put(n.getIssuer(), n.getTenantId());
        });

        return CommonResponse.<Map<String, String>>builder()
                .status(true)
                .statusCode(Constant.SUCCESS)
                .message(Constant.TENANTS_FOUND)
                .data(tenantIdMap)
                .build();

    }



    public CommonResponse<Page<TenantDetails>> findAllTenants(int pageNo, int pageSize) {
        Page<TenantDetails> tenantList = null;
        try {
            tenantList = tenantRepository.findAll(PageRequest.of(pageNo, pageSize));
            if (tenantList.isEmpty()) {
                return CommonResponse.<Page<TenantDetails>>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.NO_TENANTS)
                        .data(tenantList)
                        .build();
            }
            return CommonResponse.<Page<TenantDetails>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.TENANTS_FOUND)
                    .data(tenantList)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<Page<TenantDetails>>builder()
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