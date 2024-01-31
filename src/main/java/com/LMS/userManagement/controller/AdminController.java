package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.AdminDto;
import com.LMS.userManagement.model.Admin;
import com.LMS.userManagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/lms/api/admin")
@RequiredArgsConstructor
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
