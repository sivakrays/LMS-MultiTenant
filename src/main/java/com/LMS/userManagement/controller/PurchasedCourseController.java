package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.*;
import com.LMS.userManagement.model.PurchasedCourse;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.PurchasedCourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lms/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Purchased Course", description = "Purchased Course APIs")
public class PurchasedCourseController {

    @Autowired
    private PurchasedCourseService purchasedCourseService;

    @PostMapping("/savePurchasedCourse")
    public CommonResponse<List<PurchasedCourse>> savePurchasedCourse(@RequestBody PurchasedCourseDto purchasedCourseDto) {
        return purchasedCourseService.savePurchasedCourse(purchasedCourseDto);
    }

    @GetMapping("/getPurchasedCourse")
    public CommonResponse<List<CourseDetailDto>> getPurchasedCourseByUserId(@RequestParam Long userId) {
        return purchasedCourseService.getPurchasedCoursesByUserId(userId);
    }

    @DeleteMapping("/deletePurchasedCourse")
    public CommonResponse<String> deletePurchased(@RequestParam long id) {
        return purchasedCourseService.deletePurchased(id);
    }

    @PostMapping("/courseComplete")
    public CommonResponse<PurchasedCompletedCourseDto> courseComplete(@RequestParam String courseId, @RequestParam long userId) {
        return purchasedCourseService.courseComplete(courseId, userId);
    }

    @PostMapping("/saveCompletedSubSection")
    public CommonResponse<String> saveCompletedSection(@RequestBody SaveSubSectionDto saveSubSectionDto){
        return purchasedCourseService.saveCompletedSubSection(saveSubSectionDto);
    }

    @GetMapping("/getCourseProgress")
    public CommonResponse<ProgressBarResponseDto> getCourseProgress(@RequestParam Long userId, @RequestParam String courseid){
        return purchasedCourseService.getCourseProgress(userId, courseid);
    }

}