package com.LMS.userManagement.service;

import com.LMS.userManagement.model.Chapter;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.CourseTracker;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.CourseTrackRepository;
import com.LMS.userManagement.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseTrackService {

    private final CourseTrackRepository trackRepository;
    private final CourseRepository courseRepository;

    public CourseTrackService(CourseTrackRepository trackRepository, CourseRepository courseRepository) {
        this.trackRepository = trackRepository;
        this.courseRepository = courseRepository;
    }

    public CommonResponse<CourseTracker> saveCourseTrack(CourseTracker tracker) {
        CourseTracker courseTracker=    trackRepository.save(tracker);
        return CommonResponse.<CourseTracker>builder()
                .status(true)
                .statusCode(200)
                .message("save successfully")
                .data(courseTracker)
                .build();
    }

    public int getCourseProgress(Long userId, String courseId) {
        List<CourseTracker> trackerList=trackRepository.findByUserIdAndCourseId(userId,courseId);
        ArrayList<Integer> count=new ArrayList<>();
        if(!trackerList.isEmpty()){
         Course course= courseRepository.findByCourseId(courseId);
         if(course!=null && !course.isHtmlCourse()){
        List<Section> sectionList=     course.getSections();
             sectionList.forEach(n-> {

                 count.add(n.getSubSections().size());
             });
         }else if(course != null){
           List<Chapter> chapterList=  course.getChapters();
             chapterList.forEach(n->{
                 count.add(n.getChapterContent().size());

             });
         }
            return (trackerList.size()/count.size())*100;
        }

        return 0;
    }
}
