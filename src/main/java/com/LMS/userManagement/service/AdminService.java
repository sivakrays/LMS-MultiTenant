package com.LMS.userManagement.service;

import com.LMS.userManagement.model.Admin;
import com.LMS.userManagement.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public ResponseEntity<?> adminRegistration(Admin admin) {
   var   adminDetails= adminRepository.findAllByEmail(admin.getEmail());
        if (adminDetails.isPresent()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failed");

        }
             var savedAdmin=   adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    public ResponseEntity<?> adminLogin(String email, String password) {
       Optional<Admin> admin= adminRepository.findAllByEmail(email);
       if (admin.isPresent() && admin.get().getPassword().equals(password) ){
           return ResponseEntity.status(HttpStatus.OK).body(admin);
       }
       return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
    }
}
