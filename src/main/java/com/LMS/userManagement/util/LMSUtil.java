package com.LMS.userManagement.util;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.EducationContent;
import com.LMS.userManagement.model.EduContent;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.records.*;
import com.LMS.userManagement.repository.EduContentRepository;
import com.LMS.userManagement.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LMSUtil {

    private final HomeRepository homeRepository;

    private final EduContentRepository eduContentRepository;

    public LoginResponse findHomeScreenByTenantId(String tenantId, AuthenticationResponse auth){
        Home home= homeRepository.findByTenantId(tenantId);
      List<EducationContent> contentList= eduContentRepository.findImageByTenantId(tenantId);
        if (home==null){
            return new LoginResponse(
                    auth,
                    null
            );
        }
        List<HomeData> homeList=new ArrayList<>();
        var banner=   new Banner(
                home.getBannerImage(),
                home.getHomeTitle(),
                home.getTheme(),
                home.getSupportNumber()
        );

        var featuredCourse=   new FeaturedCourse(
                home.getCourseTitle(),
                home.getCourseTitle2(),
                home.getEducationTitle(),
                contentList
        );

        var promo= new PromoData(
                home.getPromoTitle(),
                home.getPromoVideo(),
                home.getPromoDescription()
        );

        homeList.add(new HomeData(
                banner,
                featuredCourse,
                promo
        ))   ;

     return   new LoginResponse(
                auth,
             homeList
        );
    }
}
