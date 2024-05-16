package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.records.LoginResponse;
import com.LMS.userManagement.repository.CartRepository;
import com.LMS.userManagement.util.Constant;
import com.LMS.userManagement.mapper.CustomMapper;
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
import java.util.List;


@Service
public class AuthService {

    private final CustomMapper mapper;

    private final LMSUtil lmsUtil;
    private final UserRepository userRepository;
    private final QuizRankRepository quizRankRepository;

    private final CartRepository cartRepository;

    //private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthService(CustomMapper mapper, LMSUtil lmsUtil, UserRepository userRepository, QuizRankRepository quizRankRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.mapper = mapper;
        this.lmsUtil = lmsUtil;
        this.userRepository = userRepository;
        this.quizRankRepository = quizRankRepository;
        this.cartRepository = cartRepository;
       // this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public CommonResponse<List<User>> register(RegisterRequest request) {

        try {

        User user = mapper.DtoToUserMapper(request);
        user.setProfileImage(Constant.DEFAULT_PROFILE_IMAGE);
        User savedUser = userRepository.save(user);
        List<User> users = userRepository.findAll();
        UserDTO userDto = mapper.UserDtoToUserMapper(savedUser);
            return CommonResponse.<List<User>>builder()
                    .message(Constant.USER_REGISTERED)
                    .status(true)
                    .data(users)
                    .statusCode(Constant.SUCCESS)
                    .build();

        }catch (Exception e){
            List<User> users = userRepository.findAll();
            return CommonResponse.<List<User>>builder()
                    .message(Constant.REGISTER_FAILED)
                    .status(false)
                    .data(users)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }

    }



    public CommonResponse<LoginResponse> authentication(LoginDTO loginDto, String tenantId,String type) {
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
        String profileImage=user.getProfileImage();
         int goldCount = quizRankRepository.countByUserIdAndBadge(userId, 1);
         int silverCount = quizRankRepository.countByUserIdAndBadge(userId, 2);
         int bronzeCount = quizRankRepository.countByUserIdAndBadge(userId, 3);
         Integer energyPoints = quizRankRepository.sumOfEnergyPoints(userId);
         int cartCount = cartRepository.cartCountByUserId(userId);
            if(profileImage==null || profileImage.equals(" ")){
                profileImage=Constant.DEFAULT_PROFILE_IMAGE;
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
                .profileImage(profileImage)
                .cartCount(cartCount)
                .city(user.getCity())
                .country(user.getCountry())
                .gender(user.getGender())
                .school(user.getSchool())
                .standard(user.getStandard())
                .build();

        var loginResponse=lmsUtil.findHomeScreenByTenantId(tenantId,auth,type);

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

    public CommonResponse<List<User>> getAllUser() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return CommonResponse.<List<User>>builder()
                    .status(false)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.NO_DATA)
                    .data(users)
                    .build();
        }

        return CommonResponse.<List<User>>builder()
                .status(true)
                .statusCode(Constant.SUCCESS)
                .message(Constant.DATA_FOUND)
                .data(users)
                .build();
    }


    public CommonResponse<List<User>> deleteUserById(Long userId) {

            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                List<User>  userList = userRepository.findAll();
                return CommonResponse.<List<User>>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.USER_DELETED)
                        .data(userList)
                        .build();
            }
        List<User>  userList = userRepository.findAll();
        return CommonResponse.<List<User>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.NO_DATA)
                        .data(userList)
                        .build();


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