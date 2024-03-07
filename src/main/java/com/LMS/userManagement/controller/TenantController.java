package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.TenantDto;
import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return tenantService.registerTenant(tenantDetails);
    }

    @PostMapping("/tenantLogin")
    //@PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<?> tenantLogin(@RequestHeader String email,
                                         @RequestHeader String password) {
        return tenantService.tenantLogin(email, password);
    }




}
