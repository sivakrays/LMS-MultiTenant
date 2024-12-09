package com.LMS.userManagement.service;

//import com.LMS.userManagement.awsS3.AWSS3Service;
import com.LMS.userManagement.dto.ProfileDto;
import com.LMS.userManagement.dto.UserProfileDto;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
@Service
public class ProfileService {

    private final UserRepository userRepository;

//    private final AWSS3Service awss3Service;

//    public ProfileService(UserRepository userRepository, AWSS3Service service) {
//        this.userRepository = userRepository;
//        this.awss3Service = service;
//    }

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public CommonResponse<UserProfileDto> saveAndEditProfile(ProfileDto profileRequest) {
        Optional<User> user = userRepository.findById(profileRequest.getId());
        try {
            if (user.isPresent()) {
                User userDetails = user.get();
//                if (file!=null) {
//                    String key="profileImage/"+profileRequest.getId().toString();
//                    String profileImage = awss3Service.uploadImageFile(file, key);
//                    userDetails.setProfileImage(profileImage);
//                }
                userDetails.setName(profileRequest.getName());
                userDetails.setGender(profileRequest.getGender());
                userDetails.setCity(profileRequest.getCity());
                userDetails.setCountry(profileRequest.getCountry());
                userDetails.setProfileImage(profileRequest.getProfileImage());;
                User savedUser = userRepository.save(userDetails);
                UserProfileDto profile= userRepository.findUserByUserId(savedUser.getId());
                return CommonResponse.<UserProfileDto>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.PROFILE_UPDATED)
                        .data(profile)
                        .build();
            }
                return CommonResponse.<UserProfileDto>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.USER_NOT_FOUND)
                        .build();

        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<UserProfileDto>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_PROFILE_SAVE_EDIT)
                    .error(e.getMessage())
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