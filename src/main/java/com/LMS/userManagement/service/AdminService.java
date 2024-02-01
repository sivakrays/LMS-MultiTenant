package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.AdminDto;
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

    public ResponseEntity<?> adminRegistration(AdminDto adminDto) {
   var   adminDetails= adminRepository.findAllByEmail(adminDto.getEmail());
        if (adminDetails.isPresent()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists");

        }
      var admin=  Admin.builder()
                .role("owner")
                .password(adminDto.getPassword())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .email(adminDto.getEmail())
              .build();
             var savedAdmin=   adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    public ResponseEntity<?> adminLogin(String email, String password) {
       Optional<Admin> admin= adminRepository.findAllByEmail(email);
       if (admin.isPresent() && admin.get().getPassword().equals(password) ){
          var ad= admin.get();
        var adminDto=  AdminDto.builder()
                  .password(null)
                  .email(ad.getEmail())
                  .role(ad.getRole())
                  .build();
           return ResponseEntity.status(HttpStatus.OK).body(adminDto);
       }
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found");
    }
}
