package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.PurchasedCourseDetailDto;
import com.LMS.userManagement.dto.PurchasedCourseDto;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.PurchasedCourse;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.PurchasedCourseRepository;
import com.LMS.userManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> savePurchasedCourse(PurchasedCourseDto purchasedCourseDto) {
        try {
            for (UUID courseId : purchasedCourseDto.getCourseId()) {
                PurchasedCourse purchasedCourse = new PurchasedCourse();
                purchasedCourse.setUserId(purchasedCourseDto.getUserId());
                purchasedCourse.setCourseId(courseId);
                purchasedCourse.setPurchased(true);
                purchasedCourse.setPurchasedOn(new Timestamp(System.currentTimeMillis()));
                purchasedCourseRepository.save(purchasedCourse);
            }
            return ResponseEntity.ok("Successfully Saved");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't Save");
        }
    }

    public List<Course> getPurchasedCoursesByUserId(Long userId) {
        // Find the list of purchased course IDs by user ID
        List<UUID> courseIds = purchasedCourseRepository.findCourseIdsByUserId(userId);

        // Retrieve course data for each course ID
        List<Course> courses = new ArrayList<>();
        for (UUID courseId : courseIds) {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            courseOptional.ifPresent(courses::add);
        }
        return courses;
    }



}
