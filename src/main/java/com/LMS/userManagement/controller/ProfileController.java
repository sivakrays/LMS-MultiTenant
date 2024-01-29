package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.ProfileDto;
import com.LMS.userManagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfileController {
    @Autowired
    ProfileService profileService;
    @PostMapping("/saveAndEditProfile")
    public ResponseEntity<?> saveAndEditProfile(@RequestBody ProfileDto profileRequest){
        return profileService.saveAndEditProfile(profileRequest);
    }
    @GetMapping("/getProfileById")
    public ResponseEntity<?> getProfileById(@RequestHeader Long id){
        return profileService.getProfileById(id);
    }

}
