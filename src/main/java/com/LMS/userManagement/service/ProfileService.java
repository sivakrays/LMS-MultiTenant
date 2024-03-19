package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.ProfileDto;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public ResponseEntity<?> saveAndEditProfile(ProfileDto profileRequest) {
        Optional<User> user= userRepository.findById(profileRequest.getId());
        if (user.isPresent()){
            User user1 = user.get();
            user1.setName(profileRequest.getName());
            user1.setGender(profileRequest.getGender());
            user1.setSchool(profileRequest.getSchool());
            user1.setStandard(profileRequest.getStandard());
            user1.setCity(profileRequest.getCity());
            user1.setCountry(profileRequest.getCountry());
            user1.setProfileImage(profileRequest.getProfileImage());
            User user2 = userRepository.save(user1);
            return ResponseEntity.status(HttpStatus.OK).body(user2) ;
        }
        return ResponseEntity.status(HttpStatus.OK).body("User does not found");
    }

    public ResponseEntity<?> getProfileById(Long id) {
        Optional<User> user =  userRepository.findById(id);
        if (!user.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
