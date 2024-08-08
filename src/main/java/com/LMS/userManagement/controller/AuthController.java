package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.records.LoginResponse;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lms/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management APIs")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    //  @PreAuthorize("hasAuthority('manager')")
    public CommonResponse<List<User>> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/mobile/login")
    // @PreAuthorize("hasAuthority('user')")
    public CommonResponse<LoginResponse> mobileAuthentication(@RequestBody LoginDTO loginDto, @RequestHeader String tenantId) {
        String type = "mob";
        return authService.authentication(loginDto, tenantId, type);
    }

    @PostMapping("/login")
    // @PreAuthorize("hasAuthority('user')")
    public CommonResponse<LoginResponse> WebAuthentication(@RequestBody LoginDTO loginDto, @RequestHeader String tenantId) {
        String type = "web";
        return authService.authentication(loginDto, tenantId, type);
    }

    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

    @GetMapping("/getAllUser")
    private CommonResponse<List<User>> getAllUser() {
        return authService.getAllUser();
    }

    @DeleteMapping("/deleteUserById")
    public CommonResponse<List<User>> deleteUserById(@RequestParam Long userId) {
        return authService.deleteUserById(userId);
    }

}