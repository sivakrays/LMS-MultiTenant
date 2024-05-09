package com.LMS.userManagement.util;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.dto.CourseDto;
import com.LMS.userManagement.dto.EducationContent;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.records.*;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.EduContentRepository;
import com.LMS.userManagement.repository.HomeRepository;
import com.LMS.userManagement.repository.PurchasedCourseRepository;
import com.LMS.userManagement.response.CommonResponse;
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

    private final PurchasedCourseRepository purchasedCourseRepository;

    private final CourseRepository courseRepository;

    public LMSUtil(HomeRepository homeRepository, EduContentRepository eduContentRepository, CourseService courseService, PurchasedCourseRepository purchasedCourseRepository, CourseRepository courseRepository) {
        this.homeRepository = homeRepository;
        this.eduContentRepository = eduContentRepository;
        this.courseService = courseService;
        this.purchasedCourseRepository = purchasedCourseRepository;
        this.courseRepository = courseRepository;
    }

    /*public LoginResponse findHomeScreenByTenantId(String tenantId, AuthenticationResponse auth,String type){
        Home home= homeRepository.findByTenantId(tenantId);
      List<EducationContent> contentList= eduContentRepository.findImageByTenantId(tenantId);
        Long userId = auth.getUserId();

        LinkedList<CourseDto> courseList= getAllCoursesForHome(userId);
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
*/
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

    public LinkedList<CourseDto> getAllCoursesForHome(Long userId){


        //not by siva
        //  List<CourseDetailDto> courseList=  courseRepository.findAllCourseDetailsByUserId(userId);
        // added by siva
        LinkedList<CourseDto> courseDtoList=new LinkedList<>();

        List<CourseDetailDto> courseList = courseRepository.findAllCourseDetails();
        if (courseList.isEmpty()){
            return courseDtoList;
        }
        courseList.forEach(course -> {
            Boolean purchased = purchasedCourseRepository
                    .findByCourseIdAndUserId(course.getCourse_id(),userId);
            if (purchased == null) {
                purchased = false;
            }

            CourseDto c=       CourseDto.builder()
                    .isHtmlCourse(course.getIs_html_course())
                    .courseId(course.getCourse_id())
                    .price(course.getPrice())
                    .category(course.getCategory())
                    .title(course.getTitle())
                    .createdDate(course.getCreated_date())
                    .isFree(course.getIs_free())
                    .isPurchased(purchased)
                    .ratings(course.getRatings())
                    .language(course.getLanguage())
                    .authorName(course.getAuthor_name())
                    .thumbNail(course.getThumb_nail())
                    .profileImage(course.getProfile_image())
                    .userId(course.getUser_id())
                    .build();
            courseDtoList.add(c);
        });

        return courseDtoList;

    }


    public Home findHomeScreenByTenantIdTest(String tenantId,String type){

        return homeRepository.findByTenantId(tenantId);
    }



}
