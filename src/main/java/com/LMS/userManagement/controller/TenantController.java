package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.TenantDto;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/tenant")
@Tag(name = "Tenant", description = "Tenant management APIs")

public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/registerTenant")
    public ResponseEntity<?> registerTenant(@RequestBody TenantDto tenantDetails) {
        try {
        return tenantService.registerTenant(tenantDetails);
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    }

    @PostMapping("/tenantLogin")
    //@PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<?> tenantLogin(@RequestBody LoginDTO loginDto) {
        return tenantService.tenantLogin(loginDto);
    }




}
