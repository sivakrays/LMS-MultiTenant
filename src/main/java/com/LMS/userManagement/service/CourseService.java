package com.LMS.userManagement.service;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.Quiz;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.model.SubSection;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.QuizRepository;
import com.LMS.userManagement.repository.SectionRepository;
import com.LMS.userManagement.repository.SubSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    SubSectionRepository subSectionRepository;
    @Autowired
    QuizRepository quizRepository;


    public Course searchCourseById(Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }


    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }


    public List<Course> searchCourses(String search) {
        return courseRepository.searchAllCourse(search);
    }

    public Page<Course> getAllCourses(int pageNo, int pageSize) {
        return courseRepository.findAll(PageRequest.of(pageNo,pageSize));
    }

    public ResponseEntity<?> saveSection(List<Section> sections) {
        if (sections != null) {
            return ResponseEntity.ok(sectionRepository.saveAll(sections));
        }
        return ResponseEntity.ok("Failure");
    }

    public ResponseEntity<?> deleteCourseById(Integer courseId) {
        if (courseRepository.existsById(courseId)){
            courseRepository.deleteById(courseId);
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.ok("Course not found");
    }
    public ResponseEntity<?> updateCourse(Course course) {
        return ResponseEntity.ok(courseRepository.save(course));
    }
    public ResponseEntity<?> updateSection(Section section) {
        return ResponseEntity.ok(sectionRepository.save(section));
    }
    public ResponseEntity<?> updateSubSection(SubSection subSection) {
        return ResponseEntity.ok(subSectionRepository.save(subSection));
    }
    public ResponseEntity<?> updateQuiz(Quiz quiz) {
        return ResponseEntity.ok(quizRepository.save(quiz));
    }


}
