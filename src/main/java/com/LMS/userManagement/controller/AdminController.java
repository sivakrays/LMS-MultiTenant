package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.AdminDto;
import com.LMS.userManagement.model.Admin;
import com.LMS.userManagement.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/lms/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin management APIs")

public class AdminController {

    private final AdminService adminService;




    @PostMapping("/adminRegistration")
    //@PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> adminRegistration(@RequestBody AdminDto adminDto){
     return    adminService.adminRegistration(adminDto);
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<?> adminLogin(@RequestHeader String email,
                                        @RequestHeader String password){
        return    adminService.adminLogin(email,password);
    }

    @DeleteMapping("/deleteTenantById")
    public ResponseEntity<?> deleteTenantById(@RequestHeader long id){
        try {
        return adminService.deleteTenant(id);
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    }


    @GetMapping("/getAllTenants")
    public ResponseEntity<?> getAllTenants() {
        return adminService.getAllTenants();
    }


    @GetMapping("/viewAllTenants")
    public ResponseEntity<?> findAllTenants(@RequestHeader int pageNo,@RequestHeader int pageSize) {
        return adminService.findAllTenants(pageNo,pageSize);
    }

    @PutMapping("/updateSchemaByTenant")
    public ResponseEntity<?> updateSchemaByTenant(@RequestHeader String email){
        try {
            return adminService.updateSchemaByTenant(email);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    //Testing APIs
    @GetMapping("/adminRead")
   @PreAuthorize("hasAuthority('admin')")
    public String adminRead(){

        return "admin:: can  read";
    }

@PostMapping("/adminCreate")
    @PreAuthorize("hasAuthority('admin')")
    public String adminCreate(){
        return "admin:: can create";
    }

    @DeleteMapping("/adminDelete")
    @PreAuthorize("hasAuthority('admin')")
    public String adminDelete(){

        return "admin:: can  delete";
    }



}
