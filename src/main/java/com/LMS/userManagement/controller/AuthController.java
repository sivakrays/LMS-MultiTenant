package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.dto.UserDto;
import com.LMS.userManagement.records.UserDTO;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public CommonResponse<UserDTO> register (
                            @RequestBody RegisterRequest request){
try {
    return authService.register(request);
}catch (Exception e){
    return CommonResponse.<UserDTO>builder()
            .status(false)
            .message("User already exits")
            .data(new UserDTO(null,null,null,null))
            .build();
}
}

    @PostMapping("/login")
   // @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> authentication (
            @RequestHeader String email,
            @RequestHeader String password,
            @RequestHeader String tenantId) {
        return authService.authentication(email, password,tenantId);

    }

  
    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        authService.refreshToken(request,response);
    }

    @GetMapping("/getAllUser")
    private ResponseEntity<?> getAllUser(@RequestHeader int pageNo,
                                         @RequestHeader int pageSize){
      return   authService.getAllUser(pageNo,pageSize);
    }
    @DeleteMapping("/deleteUserById")
    public ResponseEntity<?> deleteUserById(@RequestHeader Long userId){
        return authService.deleteUserById(userId);
    }


}
