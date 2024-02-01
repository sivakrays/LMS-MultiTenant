package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.TenantDto;
import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/tenant")
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

    @GetMapping("/getAllTenants")
    public ResponseEntity<?> getAllTenants() {
        return tenantService.getAllTenants();
    }



    @GetMapping("/viewAllTenants")
    public ResponseEntity<?> findAllTenants() {
        return tenantService.findAllTenants();
    }

}
