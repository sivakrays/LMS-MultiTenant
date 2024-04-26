package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.CourseTracker;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.CourseTrackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CourseTrackController {

    private final CourseTrackService trackService;

    public CourseTrackController(CourseTrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("/saveTracker")
    public CommonResponse<CourseTracker> saveCourseTrack(@RequestBody CourseTracker tracker){
            return trackService.saveCourseTrack(tracker);
        }

    @GetMapping("/getProgress")
    public int getCourseProgress(@RequestParam Long userId,
                                                     @RequestParam String courseId){
        return trackService.getCourseProgress(userId,courseId);
    }
}
