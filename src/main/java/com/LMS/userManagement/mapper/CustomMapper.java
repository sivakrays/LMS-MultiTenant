package com.LMS.userManagement.mapper;

import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.EduContent;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.records.EduContentDTO;
import com.LMS.userManagement.records.HomeDTO;
import com.LMS.userManagement.records.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomMapper {

    private final PasswordEncoder passwordEncoder;

    public CustomMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    // user register details mapper
    public User DtoToUserMapper(RegisterRequest request){

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .confirmPassword(passwordEncoder.encode(request.getConfirmPassword()))
                .role(request.getRole().toLowerCase())
                .standard(request.getStandard())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    //saved user in user registration using records
    public UserDTO UserDtoToUserMapper(User savedUser){

        return new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCreatedDate(),
                savedUser.getStandard()
        );
    }

    public Home DTOToHomeMapper(HomeDTO homeDTO,
                                String promoVideo){
        return Home.builder()
                .homeTitle(homeDTO.homeTitle())
                .courseTitle(homeDTO.courseTitle())
                .courseTitle2(homeDTO.courseTitle2())
                .bannerImage(homeDTO.bannerImage())
                .educationTitle(homeDTO.educationTitle())
                .theme(homeDTO.theme())
                .tenantId(homeDTO.tenantId())
                .promoDescription(homeDTO.promoDescription())
                .promoVideo(promoVideo)
                .standard(homeDTO.standard())
                .promoTitle(homeDTO.promoTitle())
                .supportNumber(homeDTO.supportNumber())
                .build();
    }

    public HomeDTO homeToDTOMapper(Home savedHome){

        return new HomeDTO(
                savedHome.getId(),
                savedHome.getTenantId(),
                savedHome.getHomeTitle(),
                savedHome.getCourseTitle(),
                savedHome.getCourseTitle2(),
                savedHome.getTheme(),
                savedHome.getStandard(),
                savedHome.getEducationTitle(),
                savedHome.getPromoTitle(),
                savedHome.getPromoVideo(),
                savedHome.getPromoDescription(),
                savedHome.getBannerImage(),
                savedHome.getSupportNumber(),
                savedHome.getBannerHeading(),
                savedHome.getBannerSubHeading(),
                savedHome.getBannerParagraph()
        );
    }

    public List<EduContent> DTOToEduContentMappper(List<EduContentDTO> eduContentDTOList){
        List<EduContent> eduContentList = new ArrayList<>();
        eduContentDTOList.forEach(eduContentDTO -> {
                    EduContent content = EduContent.builder()
                            .imageTitle(eduContentDTO.imageTitle())
                            .image(eduContentDTO.image())
                            .imageContent(eduContentDTO.imageContent())
                            .tenantId(eduContentDTO.tenantId())
                            .standard(eduContentDTO.standard())
                            .build();
                    eduContentList.add(content);
                });
        return eduContentList;
    }


    public List<EduContentDTO> EduContentToDTOMapper(List<EduContent> contentList) {
        List<EduContentDTO> eduContentDTOList = new ArrayList<>();

        contentList.forEach(eduContent -> {
            eduContentDTOList.add(
                    new EduContentDTO(
                            eduContent.getId(),
                            eduContent.getImageTitle(),
                            eduContent.getImage(),
                            eduContent.getImageContent(),
                            eduContent.getTenantId(),
                            eduContent.getStandard()
                    )
            );
        });
        return eduContentDTOList;
    }
}
