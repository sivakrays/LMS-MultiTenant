package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.PurchasedCourseDto;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.PurchasedCourse;
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

    public CommonResponse<PurchasedCourse> savePurchasedCourse(PurchasedCourseDto purchasedCourseDto) {
        PurchasedCourse purchasedCourseDetail = null;
        try {
            for (String courseId : purchasedCourseDto.getCourseId()) {
                PurchasedCourse purchasedCourse = new PurchasedCourse();
                purchasedCourse.setUserId(purchasedCourseDto.getUserId());
                purchasedCourse.setCourseId(courseId);
                purchasedCourse.setPurchased(true);
                purchasedCourse.setPurchasedOn(new Timestamp(System.currentTimeMillis()));

               purchasedCourseDetail = purchasedCourseRepository.save(purchasedCourse);

            }
            return CommonResponse.<PurchasedCourse>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.COURSE_PURCHASED)
                    .data(purchasedCourseDetail)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<PurchasedCourse>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_PURCHASE_COURSE)
                    .data(purchasedCourseDetail)
                    .build();
        }
    }

    public CommonResponse<List<Course>> getPurchasedCoursesByUserId(Long userId) {
        List<Course> courses = null;
        try {
            courses = new ArrayList<>();
            // Find the list of purchased course IDs by user ID
            List<String> courseIds = purchasedCourseRepository.findCourseIdsByUserId(userId);

            // If no course IDs found, return empty list
            if (courseIds.isEmpty()) {
                return CommonResponse.<List<Course>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.COURSE_IDS_EMPTY)
                        .data(courses)
                        .build();
            }
            // Retrieve course data for each course ID
            for (String courseId : courseIds) {
                Optional<Course> courseOptional = courseRepository.findById(courseId);
                courseOptional.ifPresent(courses::add);
            }
            return CommonResponse.<List<Course>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.RETRIEVED_PURCHASED_COURSE)
                    .data(courses)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<List<Course>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_RETRIEVED_PURCHASED_COURSE)
                    .data(courses)
                    .build();
        }
    }

}