package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.AdminDto;
import com.LMS.userManagement.model.Admin;
import com.LMS.userManagement.records.LoginDTO;
import com.LMS.userManagement.repository.AdminRepository;
import com.LMS.userManagement.repository.TenantRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class AdminServiceTest {

    //classed to be mocked ,injecting the dependencies
    @InjectMocks
    private AdminService adminService;

    // declaring all the dependencies
    @Mock
    private  AdminRepository adminRepository;

    @Mock
    private  TenantRepository tenantRepository;

    @Mock
    private  TenantService tenantService;
    @BeforeEach
    void setUp() {

        openMocks(this);
    }

    @Test
    public void should_save_admin_details(){

        //Given
        AdminDto adminDto =new AdminDto(
                "siva@gmail.com",
                "siva@123",
                null);

        //mocking calls
        when(adminRepository.findByEmail(adminDto.getEmail())).thenReturn(Optional.empty());
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin());
        //when
        CommonResponse<Admin> response =adminService.adminRegistration(adminDto);
        //then
        assertTrue(response.getStatus());
        assertNotNull(response.getData());
        assertEquals(Constant.SUCCESS,response.getStatusCode());

        verify(adminRepository,times(1)).findByEmail(adminDto.getEmail());
        verify(adminRepository,times(1)).save(any(Admin.class));
    }

    @Test
    public void should_not_save_admin_details(){

        AdminDto adminDto =new AdminDto(
                "siva@gmail.com",
                "siva@123",
                null);

        Admin admin= Admin.builder()
                .id(1L)
                .email("siva@gmail.com")
                .role("owner")
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .build();

        //mocking calls
        when(adminRepository.findByEmail(adminDto.getEmail())).thenReturn(Optional.of(admin));
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin());
        //when
        CommonResponse<Admin> response =adminService.adminRegistration(adminDto);
        //then
        assertFalse(response.getStatus());
        assertNull(response.getData());
        assertEquals(Constant.FORBIDDEN,response.getStatusCode());
        assertEquals("owner",admin.getRole());

        verify(adminRepository,times(1)).findByEmail(adminDto.getEmail());
        verify(adminRepository,never()).save(any(Admin.class));
    }

    @Test
    public void admin_should_login(){
        //Given
        LoginDTO loginDTO=new LoginDTO("test@gmail.com","test@123");

        Admin admin= Admin.builder()
                .id(1L)
                .email("test@gmail.com")
                .role("owner")
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .password("test@123")
                .build();

        //mock the api calls
        when(adminRepository.findByEmail(loginDTO.email())).thenReturn(Optional.of(admin));

        //when

        CommonResponse<AdminDto> response=adminService.adminLogin(loginDTO);

        //then
        assertTrue(response.getStatus());
        assertNotNull(response.getData());
        assertEquals(loginDTO.password(),admin.getPassword());
        assertEquals(Constant.SUCCESS,response.getStatusCode());
        assertEquals(Constant.LOGIN_SUCCESS,response.getMessage());


        verify(adminRepository,times(1)).findByEmail(loginDTO.email());

    }
    @Test
    public void admin_should_not_login(){
        //Given
        LoginDTO loginDTO=new LoginDTO("test@gmail.com","test@123");


        //mock the api calls
        when(adminRepository.findByEmail(loginDTO.email())).thenReturn(Optional.empty());

        //when

        CommonResponse<AdminDto> response=adminService.adminLogin(loginDTO);

        //then
        assertFalse(response.getStatus());
        assertNull(response.getData());
        assertEquals(Constant.FORBIDDEN,response.getStatusCode());
        assertEquals(Constant.USER_EXISTS,response.getMessage());


        verify(adminRepository,times(1)).findByEmail(loginDTO.email());

    }

}
