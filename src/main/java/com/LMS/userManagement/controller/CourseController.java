package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.Quiz;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.model.SubSection;
import com.LMS.userManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping("/saveCourse")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> saveCourse(@RequestBody Course course){
     return courseService.saveCourse(course);

    }
    @GetMapping("/getCourseById")
   // @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public ResponseEntity<?> getCourseById(@RequestHeader UUID courseId){
    return courseService.getCourseById(courseId);

    }
   @GetMapping("/getAllCourse")
 //  @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public ResponseEntity<?> getAllCourses(@RequestHeader int pageNo,@RequestHeader int pageSize){
       return courseService.getAllCourses(pageNo,pageSize);

    }
    @GetMapping("/searchCourses")
    public ResponseEntity<?> searchCourses(@RequestParam("search") String search){
        return courseService.searchCourses(search);

    }
    @PostMapping("/saveSection")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> saveSection(@RequestBody List<Section> sections){
        return courseService.saveSection(sections);
    }
    @DeleteMapping("/deleteCourseById")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteCourseById(@RequestHeader UUID courseId){
        return courseService.deleteCourseById(courseId);
    }
    @PutMapping("/updateCourse")
    @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }
    @PutMapping("/updateSection")
    @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateSection(@RequestBody Section section){
        return courseService.updateSection(section);
    }
    @PutMapping("/updateSubSection")
    @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateSubSection(@RequestBody SubSection subSection){
        return courseService.updateSubSection(subSection);
    }
    @PutMapping("/updateQuiz")
    @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
        return courseService.updateQuiz(quiz);
    }
    @GetMapping("/getCourseByUserId")
    public ResponseEntity<?> getCourseByUserId(@RequestHeader Long userId){
        return courseService.getCourseByUserId(userId);
    }

   /* @GetMapping("/getCourseCompletion")
    public ResponseEntity<?> getCourseCompletion(@RequestHeader int courseId){
        return courseService.getCourseCompletion(courseId);
    }*/
}

