package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.PurchasedCourseDetailDto;
import com.LMS.userManagement.dto.PurchasedCourseDto;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.service.PurchasedCourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Tag(name = "Purchased Course", description = "Purchased Course APIs")
public class PurchasedCourseController {

    @Autowired
    private PurchasedCourseService purchasedCourseService;

    @PostMapping("/savePurchasedCourse")
    public ResponseEntity<?> savePurchasedCourse(@RequestBody PurchasedCourseDto purchasedCourseDto){
        return purchasedCourseService.savePurchasedCourse(purchasedCourseDto);
    }

    @GetMapping("/getPurchasedCourse")
    public List<Course> getPurchasedCourseByUserId(@RequestParam Long userId){
        return purchasedCourseService.getPurchasedCoursesByUserId(userId);
    }

}
