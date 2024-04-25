package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.dto.PurchasedCourseDto;
import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.PurchasedCourse;
import com.LMS.userManagement.repository.CartRepository;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.PurchasedCourseRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PurchasedCourseService {

    @Autowired
    private PurchasedCourseRepository purchasedCourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    CartRepository cartRepository;

    public CommonResponse<List<PurchasedCourse>> savePurchasedCourse(PurchasedCourseDto purchasedCourseDto) {
        List<PurchasedCourse> purchasedCourseDetail = null;
        try {
            Long userId = purchasedCourseDto.getUserId();
            for (String courseId : purchasedCourseDto.getCourseId()) {
                List<PurchasedCourse>   purchasedCourseDetailList = purchasedCourseRepository.findByUserId(userId);
            if(!purchasedCourseDetailList.isEmpty()) {
                PurchasedCourse existingCourse = purchasedCourseRepository.findByUserIdAndCourseId(userId, courseId);
                if (existingCourse != null) {
                    return CommonResponse.<List<PurchasedCourse>>builder()
                            .status(false)
                            .statusCode(Constant.SUCCESS)
                            .message(Constant.COURSE_ALREADY_PURCHASED)
                            .data(purchasedCourseDetail)
                            .build();
                }
            }
                PurchasedCourse purchasedCourse = new PurchasedCourse();
                purchasedCourse.setUserId(userId);
                purchasedCourse.setCourseId(courseId);
                purchasedCourse.setPurchased(true);
                purchasedCourse.setPurchasedOn(new Timestamp(System.currentTimeMillis()));

               purchasedCourseRepository.save(purchasedCourse);
               purchasedCourseDetail = purchasedCourseRepository.findByUserId(userId);
            removePurchasedCoursesFromCart(userId,purchasedCourseDto.getCourseId());

            }
            return CommonResponse.<List<PurchasedCourse>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.COURSE_PURCHASED)
                    .data(purchasedCourseDetail)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<List<PurchasedCourse>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_PURCHASE_COURSE)
                    .data(purchasedCourseDetail)
                    .build();
        }
    }
    public void  removePurchasedCoursesFromCart(Long userId,List<String> courseIds){
        List<Cart> purchasedCartList =cartRepository.findByUserIdAndCourseId(userId,courseIds);
        cartRepository.deleteAll(purchasedCartList);
    }

    public CommonResponse<List<CourseDetailDto>> getPurchasedCoursesByUserId(Long userId) {
        List<CourseDetailDto> courseList = new ArrayList<>();
        try {
         // Find the list of purchased course IDs by user ID
          //  List<String> courseIds = purchasedCourseRepository.findCourseIdsByUserId(userId);
            courseList = purchasedCourseRepository.findPurchasedCourseByUserId(userId);
            // If no course IDs found, return empty list
            if (courseList.isEmpty()) {
                return CommonResponse.<List<CourseDetailDto>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.NO_COURSE)
                        .data(courseList)
                        .build();
            }
            // Retrieve course data for each course ID
           /* for (String courseId : courseIds) {
                Optional<CourseDetailDto> courseList = courseRepository.findAllCourseDetailsById(courseId);
                courseList.ifPresent(courses::add);
            }*/

            return CommonResponse.<List<CourseDetailDto>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.RETRIEVED_PURCHASED_COURSE)
                    .data(courseList)
                    .build();

        } catch (Exception e) {
            return CommonResponse.<List<CourseDetailDto>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.NO_COURSE)
                    .data(courseList)
                    .build();
        }
    }

    public CommonResponse<String> deletePurchased(long id) {
         purchasedCourseRepository.deleteById(id);
         return CommonResponse.<String>builder()
                 .status(true)
                 .data("course removed from purchase")
                 .build();
    }
}