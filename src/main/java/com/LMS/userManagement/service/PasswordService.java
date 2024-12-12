package com.LMS.userManagement.service;

import com.LMS.userManagement.model.Otp;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.OtpRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger = LoggerFactory.getLogger(PasswordService.class);

    public CommonResponse<String> generateAndSendOtp(String email) {
        try {
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

            // Generate OTP
            String otp = String.format("%06d", new Random().nextInt(999999));
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

            // Save or Update OTP
            Otp otpEntity = otpRepository.findByEmail(email)
                    .map(existingOtp -> {
                        existingOtp.setOtp(otp);
                        existingOtp.setExpiryTime(expiryTime);
                        return existingOtp;
                    })
                    .orElse(new Otp(email, otp, expiryTime));

            otpRepository.save(otpEntity);

            // Send OTP Email
            sendEmail(email, otp);
            logger.info("OTP sent to email {}", email);

            return CommonResponse.<String>builder()
                    .status(true)
                    .message("OTP sent successfully")
                    .statusCode(Constant.SUCCESS)
                    .build();
        } catch (Exception e) {
            logger.error("Error sending OTP to {}: {}", email, e.getMessage());
            return CommonResponse.<String>builder()
                    .status(false)
                    .message("Failed to send OTP")
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .error(e.getMessage())
                    .build();
        }
    }


    public CommonResponse<String> verifyOtp(String email, String otp) {
        try {
            Otp otpEntity = otpRepository.findByEmailAndOtp(email, otp)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid OTP"));

            if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("OTP expired");
            }

            return CommonResponse.<String>builder()
                    .status(true)
                    .message("OTP verified successfully")
                    .data(null)
                    .statusCode(Constant.SUCCESS)
                    .error(null)
                    .build();
        } catch (Exception e) {
            logger.error("Error verifying OTP for {}: {}", email, e.getMessage());
            return CommonResponse.<String>builder()
                    .status(false)
                    .message("Invalid or expired OTP")
                    .data(null)
                    .statusCode(400)
                    .error(e.getMessage())
                    .build();
        }
    }

    public CommonResponse<String> resetPassword(String email, String newPassword) {
        try {
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userRepository.save(user);
            logger.info("Password updated successfully for user: {}", email);

            return CommonResponse.<String>builder()
                    .status(true)
                    .message("Password reset successfully")
                    .data(null)
                    .statusCode(Constant.SUCCESS)
                    .error(null)
                    .build();
        } catch (Exception e) {
            logger.error("Error resetting password for {}: {}", email, e.getMessage());
            return CommonResponse.<String>builder()
                    .status(false)
                    .message("Failed to reset password")
                    .data(null)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .error(e.getMessage())
                    .build();
        }
    }

    private void sendEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for password reset is: " + otp +
                ". It will expire in 5 minutes.");
        mailSender.send(message);
    }
}
