/*
package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.mapper.CustomMapper;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.records.LoginResponse;
import com.LMS.userManagement.records.UserDTO;
import com.LMS.userManagement.repository.CartRepository;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.securityConfig.JwtService;
import com.LMS.userManagement.util.Constant;
import com.LMS.userManagement.util.LMSUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthServiceTest {

    //which service to be tested

    @InjectMocks
    private   AuthService authService;

    //declare dependency
    @Mock
    private CustomMapper mapper;
    @Mock
    private  LMSUtil lmsUtil;

    @Mock
    private  UserRepository userRepository;
    @Mock
    private  QuizRankRepository quizRankRepository;

    @Mock
    private  CartRepository cartRepository;

    @Mock
    private  JwtService jwtService;
    @Mock
    private  AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public  void should_save_a_user(){

        //Given
        RegisterRequest request=new RegisterRequest(
                "siva",
                "siva@gmail.com",
                "siva@123",
                "siva@123",
                "admin"
        );

        User user =new User();
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setRole("admin");
        user.setEmail("siva@gmail.com");
        user.setName("siva");
        user.setPassword("siva@123");
        user.setConfirmPassword("siva@123");

        //mock the calls
        //mocked dto to user mapper
        when(mapper.DtoToUserMapper(request))
                .thenReturn(user);

        //mocked user repo
        when(userRepository.save(user))
                .thenReturn(user);
        //mocked user to dto mapper
        when(mapper.UserDtoToUserMapper(user)).thenReturn(new UserDTO(
                1L,
                "siva",
                "siva@gmail.com",
                "admin",
                new Timestamp(System.currentTimeMillis())));
        //when
        CommonResponse<UserDTO> userDto= authService.register(request);

        //then
        assertEquals(request.getName(),userDto.getData().name());
        assertEquals(request.getEmail(),userDto.getData().email());

        //to verify the calls
        verify(mapper,times(1))
                .DtoToUserMapper(request);
        verify(userRepository,times(1))
                .save(user);
        verify(mapper,times(1))
                .UserDtoToUserMapper(user);


    }
*/
/*
    @Test
    public void should_login_with_email_password(){

        //given
        LoginDTO loginDTO=new LoginDTO(
                "siva@gmail.com",
                "siva@123"
        );

        String email=loginDTO.email();
        String password=loginDTO.password();

        String tenantId="public";

        //when
        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)))
                .thenReturn(null);


        CommonResponse<LoginResponse> loginResponse =authService
                .authentication(loginDTO,tenantId);


        //then
        assertNotNull(loginResponse.data.auth());
        verify(authService,timeout(2000)).authentication(loginDTO,tenantId);
    }*//*

}*/
