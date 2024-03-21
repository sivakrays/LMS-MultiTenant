package com.LMS.userManagement.util;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.dto.EducationContent;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.records.*;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.EduContentRepository;
import com.LMS.userManagement.repository.HomeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LMSUtil {

    private final HomeRepository homeRepository;

    private final EduContentRepository eduContentRepository;

    private final CourseRepository courseRepository;

    public LMSUtil(HomeRepository homeRepository, EduContentRepository eduContentRepository, CourseRepository courseRepository) {
        this.homeRepository = homeRepository;
        this.eduContentRepository = eduContentRepository;
        this.courseRepository = courseRepository;
    }

    public LoginResponse findHomeScreenByTenantId(String tenantId, AuthenticationResponse auth){
        Home home= homeRepository.findByTenantId(tenantId);
      List<EducationContent> contentList= eduContentRepository.findImageByTenantId(tenantId);

         List<CourseDetailDto> courseList= courseRepository.findAllCourseDetails();
        List<HomeData> homeList=new ArrayList<>();

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
        if (home==null && contentList.isEmpty() && courseList.isEmpty()){
            return new LoginResponse(
                    auth,
                    homeList
            );
        }


        Banner banner = ExtractBannerDetailFromHome(home);


      //  List<CourseData> popularCourseList=new ArrayList<>();
        var popularCourse=   new PopularCourse(
                    home.getCourseTitle(),
                   courseList,
                false
        );

      //  List<CourseData> recommendedCourseList=new ArrayList<>();
        var recommendedCourse=   new RecommendedCourse(
                home.getCourseTitle2(),
                courseList,
                false
        );


        var educationContent=new EducationCourse(
                home.getEducationTitle(),
                educationContentList,
                true
        );

        var promo= new PromoData(
                home.getPromoTitle(),
                home.getPromoVideo(),
                home.getPromoDescription()
        );

        List<Object> featuredCourseList=new ArrayList<>();
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
