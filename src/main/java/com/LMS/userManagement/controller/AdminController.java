package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.AdminDto;
import com.LMS.userManagement.model.Admin;
import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/lms/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin management APIs")

public class AdminController {

    private final AdminService adminService;

    @PostMapping("/adminRegistration")
    //@PreAuthorize("hasAuthority('admin')")
    public CommonResponse<Admin> adminRegistration(@RequestBody AdminDto adminDto){
        return adminService.adminRegistration(adminDto);
    }

    @PostMapping("/adminLogin")
    public CommonResponse<AdminDto> adminLogin(@RequestBody LoginDTO loginDto){
        return adminService.adminLogin(loginDto);
    }

    @DeleteMapping("/deleteTenantById")
    public CommonResponse<List<TenantDetails>> deleteTenantById(@RequestParam long id){
        return adminService.deleteTenant(id);
    }


    @GetMapping("/getAllTenants")
    public CommonResponse<Map<String, String>> getAllTenants() {
        return adminService.getAllTenants();
    }


    @GetMapping("/viewAllTenants")
    public CommonResponse<List<TenantDetails>> findAllTenants() {
        return adminService.findAllTenants();
    }

    @PutMapping("/updateSchemaByTenant")
    public CommonResponse<Optional<TenantDetails>> updateSchemaByTenant(@RequestParam String email){
        return adminService.updateSchemaByTenant(email);
    }


  //  @GetMapping("/")


}