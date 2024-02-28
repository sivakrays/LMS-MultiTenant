package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.records.LoginResponse;
import com.LMS.userManagement.util.Constant;
import com.LMS.userManagement.util.CustomMapper;
import com.LMS.userManagement.records.UserDTO;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.securityConfig.JwtService;
import com.LMS.userManagement.util.LMSUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static software.amazon.awssdk.services.s3.model.IntelligentTieringConfiguration.builder;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomMapper mapper;

    private final LMSUtil lmsUtil;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    QuizRankRepository quizRankRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public CommonResponse<UserDTO> register(RegisterRequest request) {
        UserDTO userDto;
        try {
            User user=mapper.UserMapper(request);
            var savedUser= userRepository.save(user);
            userDto=mapper.UserDTOMapper(savedUser);
        }catch (Exception e){
            return CommonResponse.<UserDTO>builder()
                    .status(false)
                    .message(Constant.USER_EXISTS)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }

        return CommonResponse.<UserDTO>builder()
                .message(Constant.USER_REGISTERED)
                .status(true)
                .data(userDto)
                .statusCode(Constant.SUCCESS)
                .build();

    }



    public CommonResponse<LoginResponse> authentication(LoginDTO loginDto, String tenantId) {
        String email = loginDto.email();
        String password = loginDto.password();
        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email, password
                )
        );
        } catch (Exception e) {
            return CommonResponse.<LoginResponse>builder()
                    .status(false)
                    .statusCode(Constant.FORBIDDEN)
                    .message(Constant.LOGIN_FAILED)
                    .build();
        }

        var user = userRepository.findByEmail(email);
        long userId = user.getId();
        int goldCount;
        int silverCount;
        int bronzeCount;
        Integer energyPoints;

            goldCount = quizRankRepository.countByUserIdAndBadge(userId, 1);
            silverCount = quizRankRepository.countByUserIdAndBadge(userId, 2);
            bronzeCount = quizRankRepository.countByUserIdAndBadge(userId, 3);
            energyPoints = quizRankRepository.sumOfEnergyPoints(userId);


        String jwtToken = jwtService.generateToken(user, tenantId);
        var auth = AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .gold(goldCount)
                .silver(silverCount)
                .bronze(bronzeCount)
                .energyPoints(energyPoints)
                .build();

        var loginResponse=lmsUtil.findHomeScreenByTenantId(tenantId,auth);

        return CommonResponse.<LoginResponse>builder()
                .status(true)
                .statusCode(Constant.SUCCESS)
                .message(Constant.LOGIN_SUCCESS)
                .data(loginResponse)
                .build();
    }



    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return;
        }

        refreshToken=authHeader.substring(7);
        userEmail=jwtService.extractUsername(refreshToken);
        String tenantId=   jwtService.extractTenantId(refreshToken);
        if(userEmail!=null && tenantId!=null){
            var user=this.userRepository.findByEmail(userEmail);
            if(jwtService.isTokenValid(refreshToken,user)){
                String accessToken=jwtService.generateToken(user,tenantId);
                //     revokeAllUserTokens(user);
                //    saveUserToken(user,accessToken);
                var authResponse= AuthenticationResponse.builder()
                        .token(accessToken)
                        //.refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }

    }

    public CommonResponse<Page<User>> getAllUser(int pageNo, int pageSize) {
        Page<User> users = null;
        try {
            Pageable sortedByTime =
                    PageRequest.of(pageNo, pageSize, Sort.by(Constant.CREATED_DATE).descending());
            users = userRepository.findAll(sortedByTime);
        } catch (Exception e) {
            return CommonResponse.<Page<User>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_RETRIEVE_USERS)
                    .build();
        }
        if (users.isEmpty()) {
            return CommonResponse.<Page<User>>builder()
                    .status(false)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.NO_DATA)
                    .build();
        }

        return CommonResponse.<Page<User>>builder()
                .status(true)
                .statusCode(Constant.SUCCESS)
                .message(Constant.DATA_FOUND)
                .data(users)
                .build();
    }


    public CommonResponse<Page<User>> deleteUserById(Long userId,int pageNo,int pageSize) {
        try {
            Page<User> userList = null;
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
               userList = userRepository.findAll(PageRequest.of(pageNo,pageSize));
                return CommonResponse.<Page<User>>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.USER_DELETED)
                        .data(userList)
                        .build();
            } else {
                return CommonResponse.<Page<User>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.NO_DATA)
                        .data(userList)
                        .build();
            }
        } catch (Exception e) {
            return CommonResponse.<Page<User>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_DELETE_USER)
                    .data(null)
                    .build();
        }
    }

    /*private void saveUserToken(User user, String jwtToken) {
        var token=Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType("BEARER")
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens=tokenRepository.findAllvalidTokensByUser(user.getId());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }*/
}