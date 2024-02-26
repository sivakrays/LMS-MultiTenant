package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.Quiz;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.model.SubSection;
import com.LMS.userManagement.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Course", description = "Course management APIs")

public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping("/saveCourse")
  //  @PreAuthorize("hasAuthority('admin')")
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
    public ResponseEntity<?> getAllCourses(@RequestHeader int pageNo,@RequestHeader int pageSize) throws InterruptedException {
        return courseService.getAllCourses(pageNo,pageSize);

    }
    @GetMapping("/searchCourses")
    public ResponseEntity<?> searchCourses(@RequestParam("search") String search,@RequestHeader(defaultValue = "0") int pageNo,@RequestHeader(defaultValue = "6") int pageSize){
        return courseService.searchCourses(search,pageNo,pageSize);

    }
    @PostMapping("/saveSection")
   // @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> saveSection(@RequestBody List<Section> sections){
        return courseService.saveSection(sections);
    }
    @DeleteMapping("/deleteCourseById")
  //  @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteCourseById(@RequestHeader UUID courseId,@RequestHeader int pageNo,@RequestHeader int pageSize){
        return courseService.deleteCourseById(courseId,pageNo,pageSize);
    }
    @PutMapping("/updateCourse")
   // @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }
    @PutMapping("/updateSection")
   // @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateSection(@RequestBody Section section){
        return courseService.updateSection(section);
    }
    @PutMapping("/updateSubSection")
   // @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateSubSection(@RequestBody SubSection subSection){
        return courseService.updateSubSection(subSection);
    }
    @PutMapping("/updateQuiz")
 //   @PreAuthorize("hasAuthority('admin')")
    public  ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
        return courseService.updateQuiz(quiz);
    }
    @GetMapping("/getCourseByUserId")
    public ResponseEntity<?> getCourseByUserId(@RequestHeader Long userId,@RequestHeader int pageNo,@RequestHeader int pageSize){
        return courseService.getCourseByUserId(userId,pageNo,pageSize);
    }

   /* @GetMapping("/getCourseCompletion")
    public ResponseEntity<?> getCourseCompletion(@RequestHeader int courseId){
        return courseService.getCourseCompletion(courseId);
    }*/
}

