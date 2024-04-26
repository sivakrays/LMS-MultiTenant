package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.dto.CourseDto;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.records.CourseDTO;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Tag(name = "Course", description = "Course management APIs")

public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping(value = "/saveCourse" ,consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
  //  @PreAuthorize("hasAuthority('admin')")
    public CommonResponse<Course> saveCourse(@RequestPart(value ="course") Course course,
                                            @RequestPart(value = "file") MultipartFile file){
     return courseService.saveCourse(course,file);

    }
    @GetMapping("/getCourseById")
   // @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public CommonResponse<CourseDTO> getCourseById(@RequestParam String courseId,@RequestParam Long userId){
    return courseService.getCourseById(courseId,userId);


    }
   @GetMapping("/getAllCourse")
 //  @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    public CommonResponse<LinkedList<CourseDto>> getAllCourses(@RequestParam Long userId) throws InterruptedException {
        return courseService.getAllCourses(userId);

    }
    @GetMapping("/searchCourses")
    public CommonResponse<List<CourseDetailDto> > searchCourses(@RequestParam("search") String search){
        return courseService.searchCourses(search);

    }
    @PostMapping("/saveSection")
   // @PreAuthorize("hasAuthority('admin')")
    public CommonResponse<List<Section>>saveSection(@RequestBody List<Section> sections){
        return courseService.saveSection(sections);
    }
    @DeleteMapping("/deleteCourseById")
  //  @PreAuthorize("hasAuthority('admin')")
    public CommonResponse<List<Course>> deleteCourseById(@RequestParam String courseId){
        return courseService.deleteCourseById(courseId);
    }
    @PutMapping("/updateCourse")
   // @PreAuthorize("hasAuthority('admin')")
    public  CommonResponse<Course> updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }
    @PutMapping("/updateSection")
   // @PreAuthorize("hasAuthority('admin')")
    public  CommonResponse<Section>updateSection(@RequestBody Section section){
        return courseService.updateSection(section);
    }
    @PutMapping("/updateSubSection")
   // @PreAuthorize("hasAuthority('admin')")
    public  CommonResponse<SubSection> updateSubSection(@RequestBody SubSection subSection){
        return courseService.updateSubSection(subSection);
    }
    @PutMapping("/updateQuiz")
 //   @PreAuthorize("hasAuthority('admin')")
    public  CommonResponse<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return courseService.updateQuiz(quiz);
    }
    @GetMapping("/getCourseByUserId")
    public CommonResponse<List<Course>> getCourseByUserId(@RequestParam Long userId){
        return courseService.getCourseByUserId(userId);
    }

    @PostMapping("/saveHtmlCourse")
    public CommonResponse<List<Chapter>> saveCourse(@RequestBody List<Chapter> chapterList) {
        return courseService.saveHtmlCourse(chapterList);
    }
    @PutMapping("/updateChapter")
    public CommonResponse<Chapter> updateChapter(@RequestBody Chapter chapter) {
        return courseService.updateChapter(chapter);
    }

    @PutMapping("/updateChapterContent")
    public CommonResponse<ChapterContent> updateChapterContent(@RequestBody ChapterContent chapterContent) {
        return courseService.updateChapterContent(chapterContent);
    }
   /* @GetMapping("/getCourseCompletion")
    public ResponseEntity<?> getCourseCompletion(@RequestHeader int courseId){
        return courseService.getCourseCompletion(courseId);
    }*/
}

