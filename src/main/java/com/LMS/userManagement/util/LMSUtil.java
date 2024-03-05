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
      List<CourseData> educationContentList=new ArrayList<>();
      if (!contentList.isEmpty()) {
          contentList.forEach(n -> {
              educationContentList.add(
                      new CourseData(
                              n.getImage_title(),
                              n.getImage(),
                              n.getImage_content()));
          });
      }
        if (home==null){
            return new LoginResponse(
                    auth,
                    null
            );
        }


        List<HomeData> homeList=new ArrayList<>();

        Banner banner= ExtractBannerDetailFromHome(home);


        List<CourseData> popularCourseList=new ArrayList<>();
        var popularCourse=   new FeaturedCourse(
                    home.getCourseTitle(),
                   popularCourseList,
                false
        );

        List<CourseData> recommendedCourseList=new ArrayList<>();
        var recommendedCourse=   new FeaturedCourse(
                home.getCourseTitle2(),
                recommendedCourseList,
                false
        );


        var educationContent=new FeaturedCourse(
                home.getEducationTitle(),
                educationContentList,
                true
        );

        var promo= new PromoData(
                home.getPromoTitle(),
                home.getPromoVideo(),
                home.getPromoDescription()
        );

        List<FeaturedCourse> featuredCourseList=new ArrayList<>();
        featuredCourseList.add(popularCourse);
        featuredCourseList.add(recommendedCourse);
        featuredCourseList.add(educationContent);

        homeList.add(new HomeData(
                banner,
                featuredCourseList,
                promo
        ))   ;

     return   new LoginResponse(
                auth,
             homeList
        );
    }

    private Banner ExtractBannerDetailFromHome(Home home) {
        return  new Banner(
                home.getBannerImage(),
                home.getHomeTitle(),
                home.getTheme(),
                home.getSupportNumber()
        );
    }
}
