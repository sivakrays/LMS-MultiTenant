package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.TenantDto;
import com.LMS.userManagement.model.TenantDetails;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/tenant")
@Tag(name = "Tenant", description = "Tenant management APIs")

public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/registerTenant")
    public CommonResponse<List<TenantDetails>> registerTenant(@RequestBody TenantDto tenantDetails) throws Exception {
        return tenantService.registerTenant(tenantDetails);
    }

    @PostMapping("/tenantLogin")
    //@PreAuthorize("hasAuthority('manager')")
    public CommonResponse<TenantDto> tenantLogin(@RequestBody LoginDTO loginDto) {
        return tenantService.tenantLogin(loginDto);
    }

}