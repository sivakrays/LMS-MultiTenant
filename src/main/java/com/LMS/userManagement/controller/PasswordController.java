package com.LMS.userManagement.controller;

import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/send-otp")
    public CommonResponse<String> sendOtp(@RequestParam String email) {
        return passwordService.generateAndSendOtp(email);
    }

    @PostMapping("/verify-otp")
    public CommonResponse<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return passwordService.verifyOtp(email, otp);
    }

    @PutMapping("/reset-password")
    public CommonResponse<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        return passwordService.resetPassword(email, newPassword);
    }
}
