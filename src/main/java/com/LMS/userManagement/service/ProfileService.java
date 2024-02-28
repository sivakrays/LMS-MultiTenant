package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.ProfileDto;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public CommonResponse<User> saveAndEditProfile(ProfileDto profileRequest) {
        Optional<User> user = userRepository.findById(profileRequest.getId());
        User savedUser = null;
        try {
            if (user.isPresent()) {
                User user1 = user.get();
                user1.setName(profileRequest.getName());
                user1.setGender(profileRequest.getGender());
                user1.setSchool(profileRequest.getSchool());
                user1.setStandard(profileRequest.getStandard());
                user1.setCity(profileRequest.getCity());
                user1.setCountry(profileRequest.getCountry());
                savedUser = userRepository.save(user1);
                return CommonResponse.<User>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.PROFILE_UPDATED)
                        .data(savedUser)
                        .build();
            } else {
                return CommonResponse.<User>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.USER_NOT_FOUND)
                        .data(savedUser)
                        .build();
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<User>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_PROFILE_SAVE_EDIT)
                    .data(savedUser)
                    .build();
        }
    }

    public CommonResponse<User> getProfileById(Long id) {
        Optional<User> user = null;
        try {
            user = userRepository.findById(id);
            if (user.isPresent()) {
                return CommonResponse.<User>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.PROFILE_FOUND)
                        .data(user.get())
                        .build();
            } else {
                return CommonResponse.<User>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.PROFILE_NOT_FOUND)
                        .data(null)
                        .build();
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<User>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_RETRIEVED_PROFILE)
                    .data(null)
                    .build();
        }
    }

}