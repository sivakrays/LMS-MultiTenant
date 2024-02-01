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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> saveSection(List<Section> sections) {
        if (sections != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sectionRepository.saveAll(sections));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("course already exists");
    }

    public ResponseEntity<?> deleteCourseById(Integer courseId) {
        if (courseRepository.existsById(courseId)){
            courseRepository.deleteById(courseId);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Course not found");
    }
    public ResponseEntity<?> updateCourse(Course course) {
        return ResponseEntity.status(HttpStatus.OK).body(courseRepository.save(course));
    }
    public ResponseEntity<?> updateSection(Section section) {
        return ResponseEntity.status(HttpStatus.OK).body(sectionRepository.save(section));
    }
    public ResponseEntity<?> updateSubSection(SubSection subSection) {
        return ResponseEntity.status(HttpStatus.OK).body(subSectionRepository.save(subSection));
    }
    public ResponseEntity<?> updateQuiz(Quiz quiz) {

        return ResponseEntity.status(HttpStatus.OK).body(quizRepository.save(quiz));
    }


    public ResponseEntity<?> saveCourse(Course course) {
        Course course1 = courseRepository.findCourseByCourseId(course.getCourseId());
        if(course1 != null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Course already exists");
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));

    }

    public ResponseEntity<?> searchCourseById(Integer courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        if(course != null){
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Course not found");
    }

    public ResponseEntity<?> getAllCourses(int pageNo, int pageSize) {
        Page<Course> course = courseRepository.findAll(PageRequest.of(pageNo,pageSize));
        if(course != null){
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Course not found");

    }

    public ResponseEntity<?> searchCourses(String search) {
        if (search.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
        }
        List<Course> courses =courseRepository.searchAllCourse(search);
        if(courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }
}
