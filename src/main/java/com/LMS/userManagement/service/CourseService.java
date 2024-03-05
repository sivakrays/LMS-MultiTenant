package com.LMS.userManagement.service;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    HtmlCourseRepository htmlCourseRepository;

    public ResponseEntity<?> saveSection(List<Section> sections) {
        List<Section> sectionList = sectionRepository.saveAll(sections);
        return ResponseEntity.status(HttpStatus.OK).body(sectionList);
    }

    public ResponseEntity<?> deleteCourseById(UUID courseId,int pageNo,int pageSize) {

        if (courseRepository.existsById(courseId)){
            Optional<Course> course = courseRepository.findById(courseId);
            Long userId = course.get().getUserId();
            courseRepository.deleteById(courseId);
            Page<Course> courses = courseRepository.findCourseByUserId(userId,PageRequest.of(pageNo,pageSize));
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Course not found");
    }
    public ResponseEntity<?> updateCourse(Course course) {
        Course course1 = courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).body(course1);
    }
    public ResponseEntity<?> updateSection(Section section) {
        Section section1 = sectionRepository.save(section);
        return ResponseEntity.status(HttpStatus.OK).body(section1);
    }
    public ResponseEntity<?> updateSubSection(SubSection subSection) {
        SubSection subSection1 =subSectionRepository.save(subSection);
        return ResponseEntity.status(HttpStatus.OK).body(subSection1);
    }
    public ResponseEntity<?> updateQuiz(Quiz quiz) {
        Quiz quiz1 =quizRepository.save(quiz);
        return ResponseEntity.status(HttpStatus.OK).body(quiz1);
    }


    public ResponseEntity<?> saveCourse(Course course) {
        Course course1 = courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).body(course1);

    }

    public ResponseEntity<?> getCourseById(UUID courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        if(course != null){
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
            return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    public ResponseEntity<?> getAllCourses(int pageNo, int pageSize) {
        List<Object> courseList=new ArrayList<>();
        Page<Course> course = courseRepository.findAll(PageRequest.of(pageNo,pageSize));
        Page<HtmlCourse> htmlCourses=htmlCourseRepository.findAll(PageRequest.of(pageNo,pageSize));
        courseList.add(course.getContent());
        courseList.add(htmlCourses.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(courseList);

    }

    public ResponseEntity<?> searchCourses(String search,int pageNo,int pageSize) {
        if (search.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
        }
        Page<Course> courses =courseRepository.searchAllCourse(search,PageRequest.of(pageNo, pageSize));
        if(courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    public ResponseEntity<?> getCourseByUserId(Long userId,int pageNo, int pageSize) {
        Page<Course> courses =courseRepository.findCourseByUserId(userId,PageRequest.of(pageNo, pageSize));
        if(!courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    public ResponseEntity<?> saveHtmlCourse(List<HtmlCourse> course) {
    var htmlCourse=    htmlCourseRepository.saveAll(course);
       return  ResponseEntity.ok(htmlCourse);
    }
/*
    public ResponseEntity<?> getCourseCompletion(int courseId) {
        var courseResponse=   getCourseById(courseId);
      if (courseResponse.getStatusCode().value()!=200){
          return null;
      }
      Course course= (Course) courseResponse.getBody();

      //logic to find over course content
    List<Section> sectionList=  course.getSections();
        List<Long> countList = sectionList.stream()
                .flatMap(section -> Stream.of((long) section.getSubSections().size(),
                        section.getSubSections()
                                .stream()
                                .flatMap(subSection -> subSection.getQuizList().stream())
                                .count()))
                .collect(Collectors.toList());

//sum of over all course content
        int sum1 = countList.stream().mapToInt(Long::intValue).sum();

        //logic to find the viewed course content
       *//* List<Integer> count = sectionList.stream()
                .flatMap(section -> section.getSubSections().stream())
                .map(SubSection::getWatched)
                .filter(watched -> watched == 1)
                .collect(Collectors.toList());

        sectionList.stream()
                .flatMap(section -> section.getSubSections().stream())
                .flatMap(subSection -> subSection.getQuizList().stream())
                .map(Quiz::getWatched)
                .filter(watched -> watched == 1)
                .forEach(count::add);
*//*
        List<Integer> count=new ArrayList<>();
        sectionList.forEach(section->{
            section.getSubSections().forEach(subSec ->{
               int watched= subSec.getWatched();
                if (watched==1){
                    count.add(watched);
                }
                subSec.getQuizList().forEach(quiz->{
                  int qWatched=  quiz.getWatched();
                  if(watched==1){
                      count.add(qWatched);
                  }
                });


            });
        });

        int sum2 = count.stream().mapToInt(Integer::intValue).sum();

   int courseViewed=(sum2/sum1)*100;

        return ResponseEntity.ok(courseViewed);
    }*/
}
