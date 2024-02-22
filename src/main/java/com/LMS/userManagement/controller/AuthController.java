package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.records.LoginDto;
import com.LMS.userManagement.service.AuthService;
import com.LMS.userManagement.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/lms/api/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management APIs")

public class AuthController {


    @Autowired
    private  AuthService authService;




    @PostMapping("/register")
  //  @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<?> register (
                            @RequestBody RegisterRequest request){
try {
    return authService.register(request);
}catch (Exception e){
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists");
}
}

    @PostMapping("/login")
   // @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> authentication (
            @RequestBody LoginDto loginDto,
            @RequestHeader String tenantId) {
        return authService.authentication(loginDto,tenantId);

    }

  
    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        authService.refreshToken(request,response);
    }

    @GetMapping("/getAllUser")
    //@JsonView(Views.MyResponseViews.class)
    private ResponseEntity<?> getAllUser(@RequestParam int pageNo,
                                         @RequestParam int pageSize){
      return   authService.getAllUser(pageNo,pageSize);
    }
    @DeleteMapping("/deleteUserById")
    public ResponseEntity<?> deleteUserById(@RequestParam Long userId){
        return authService.deleteUserById(userId);
    }


}
