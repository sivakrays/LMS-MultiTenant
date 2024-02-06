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
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (!sections.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sectionRepository.saveAll(sections));
        }
        return ResponseEntity.status(409).body("course already exists");
    }

    public ResponseEntity<?> deleteCourseById(UUID courseId) {
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
            return ResponseEntity.status(409).body("Course already exists");
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));

    }

    public ResponseEntity<?> getCourseById(UUID courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        if(course != null){
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(course);
    }

    public ResponseEntity<?> getAllCourses(int pageNo, int pageSize) {
        Page<Course> course = courseRepository.findAll(PageRequest.of(pageNo,pageSize));
        if(!course.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
            return ResponseEntity.status(HttpStatus.OK).body(course);

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

    public ResponseEntity<?> getCourseByUserId(Long userId) {
        List<Course> courses =courseRepository.findCourseByUserId(userId);
        if(!courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courses);
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
