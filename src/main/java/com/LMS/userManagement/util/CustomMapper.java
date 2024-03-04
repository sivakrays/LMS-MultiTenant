package com.LMS.userManagement.util;

import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.EduContent;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.records.EduContentDTO;
import com.LMS.userManagement.records.HomeDTO;
import com.LMS.userManagement.records.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomMapper {

    private final PasswordEncoder passwordEncoder;



    // user register details mapper
    public User UserMapper(RegisterRequest request){
        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .confirmPassword(passwordEncoder.encode(request.getConfirmPassword()))
                .role(request.getRole().toLowerCase())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .build();

        return user;
    }

    //saved user in user registration using records
    public UserDTO UserDTOMapper(User savedUser){
        UserDTO userDto= new UserDTO(
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.role,
                savedUser.createdDate
        );

        return userDto;
    }

    public Home DTOToHomeMapper(HomeDTO homeDTO,
                                String promoVideo){
        Home home=Home.builder()
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
        return home;
    }

    public HomeDTO homeToDTOMapper(Home savedHome){

        HomeDTO homeDTO=   new HomeDTO(
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
                savedHome.getSupportNumber()
        );
        return homeDTO;
    }

    public List<EduContent> DTOToEduContentMappper(List<EduContentDTO> eduContentDTOList){
        List<EduContent> eduContentList = new ArrayList<>();
        eduContentDTOList.forEach(eduContentDTO -> {
                    EduContent content = EduContent.builder()
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
