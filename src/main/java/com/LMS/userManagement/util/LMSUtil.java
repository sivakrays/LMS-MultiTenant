package com.LMS.userManagement.util;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.CourseDto;
import com.LMS.userManagement.dto.EducationContent;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.records.*;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.EduContentRepository;
import com.LMS.userManagement.repository.HomeRepository;
import com.LMS.userManagement.service.CourseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class LMSUtil {

    private final HomeRepository homeRepository;

    private final EduContentRepository eduContentRepository;

    private final CourseService courseService;

    private final CourseRepository courseRepository;

    public LMSUtil(HomeRepository homeRepository, EduContentRepository eduContentRepository, CourseService courseService, CourseRepository courseRepository) {
        this.homeRepository = homeRepository;
        this.eduContentRepository = eduContentRepository;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    public LoginResponse findHomeScreenByTenantId(String tenantId, AuthenticationResponse auth,String type){
        Home home= homeRepository.findByTenantId(tenantId);
      List<EducationContent> contentList= eduContentRepository.findImageByTenantId(tenantId);
        Long userId = auth.getUserId();

        LinkedList<CourseDto> courseList=new LinkedList<>();

       /* CommonResponse<LinkedList<CourseDto>> courseResponse= courseService.getAllCourses(userId);
        LinkedList<CourseDto> courseList= courseResponse.getData();*/


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
        Banner banner=new Banner("","","","","","","");
        PromoData promo =new PromoData("","","");
        if (home==null && contentList.isEmpty() && courseList.isEmpty()){
            homeList.add(new HomeData(
                    banner,
                    new ArrayList<>(),
                    promo
            ))   ;
            return new LoginResponse(
                    auth,
                    homeList
            );
        }


        String courseTitle="";
        String courseTitle2="";
        String educationTitle="";


        if (home!=null){
            courseTitle=home.getCourseTitle();
            courseTitle2=home.getCourseTitle2();
            educationTitle= home.getEducationTitle();
            if(type.equalsIgnoreCase("web")){
                banner = ExtractWebBannerDetailFromHome(home);
            }else{
                banner= new Banner(
                        home.getBannerImage(),
                        home.getHomeTitle(),
                        home.getTheme(),
                       "",
                        "",
                        "",
                        home.getSupportNumber()
                );
            }
             promo= new PromoData(
                    home.getPromoTitle(),
                    home.getPromoVideo(),
                    home.getPromoDescription()
            );
        }

      //  List<CourseData> popularCourseList=new ArrayList<>();
        var popularCourse=   new PopularCourse(
                   courseTitle,
                   courseList,
                false
        );

      //  List<CourseData> recommendedCourseList=new ArrayList<>();
        var recommendedCourse=   new RecommendedCourse(
                courseTitle2,
                courseList,
                false
        );


        var educationContent=new EducationCourse(
                educationTitle,
                educationContentList,
                true
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

    private Banner ExtractWebBannerDetailFromHome(Home home) {
        return  new Banner(
                home.getBannerImage(),
                home.getHomeTitle(),
                home.getTheme(),
                home.getBannerHeading(),
                home.getBannerSubHeading(),
                home.getBannerParagraph(),
                home.getSupportNumber()
        );
    }
}
