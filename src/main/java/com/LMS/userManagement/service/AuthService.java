package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.util.Constant;
import com.LMS.userManagement.util.Mapper;
import com.LMS.userManagement.records.UserDTO;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.securityConfig.JwtService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Mapper mapper;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    QuizRankRepository quizRankRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public CommonResponse<UserDTO> register(RegisterRequest request) {
       User user=mapper.UserMapper(request);
       var savedUser= userRepository.save(user);
        UserDTO userDto=mapper.UserDTOMapper(savedUser);
        return CommonResponse.<UserDTO>builder()
                .message(Constant.USER_REGISTERED)
                .status(true)
                .data(userDto)
                .statusCode(Constant.SUCCESS)
                .build();

    }



    public CommonResponse<?> authentication(LoginDTO loginDto, String tenantId) {
        String email = loginDto.email();
        String password = loginDto.password();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email, password
                )
        );

        var user = userRepository.findByEmail(email);
        long userId = user.getId();
        int goldCount;
        int silverCount;
        int bronzeCount;
        Integer energyPoints;
        try {
            goldCount = quizRankRepository.countByUserIdAndBadge(userId, 1);
            silverCount = quizRankRepository.countByUserIdAndBadge(userId, 2);
            bronzeCount = quizRankRepository.countByUserIdAndBadge(userId, 3);
            energyPoints = quizRankRepository.sumOfEnergyPoints(userId);
        } catch (Exception e) {
            return CommonResponse.builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_USER_STATS)
                    .data(null)
                    .build();
        }

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

        return CommonResponse.builder()
                .status(true)
                .statusCode(Constant.SUCCESS)
                .message(Constant.AUTHENTICATED)
                .data(auth)
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
                    PageRequest.of(pageNo, pageSize, Sort.by("createdDate").descending());
            users = userRepository.findAll(sortedByTime);

            if (users.isEmpty()) {
                return CommonResponse.<Page<User>>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.USERS_NOT_FOUND)
                        .data(users)
                        .build();
            }

            return CommonResponse.<Page<User>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.USERS_FOUND)
                    .data(users)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<Page<User>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_RETRIEVE_USERS)
                    .data(users)
                    .build();
        }
    }


    public CommonResponse<?> deleteUserById(Long userId) {
        try {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                return CommonResponse.builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.DELETE_USER)
                        .data(null)
                        .build();
            } else {
                return CommonResponse.builder()
                        .status(false)
                        .statusCode(Constant.NOT_FOUND)
                        .message(Constant.NO_USER)
                        .data(null)
                        .build();
            }
        } catch (Exception e) {
            return CommonResponse.builder()
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
