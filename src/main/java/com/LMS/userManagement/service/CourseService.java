package com.LMS.userManagement.service;
import com.LMS.userManagement.dto.HtmlCourseDto;
import com.LMS.userManagement.dto.ChapterContents;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
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
    private HtmlCourseRepository htmlCourseRepository;

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
      //  List<Object> courseList=new ArrayList<>();
        Page<Course> course = courseRepository.findAll(PageRequest.of(pageNo,pageSize));
      //  Page<HtmlCourse> htmlCourses=htmlCourseRepository.findAll(PageRequest.of(pageNo,pageSize));
       // courseList.add(course.getContent());
      //  courseList.add(htmlCourses.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(course);

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

    public ResponseEntity<?> saveHtmlCourse(List<HtmlCourseDto> htmlCourseDtoList) {
        try {
            List<HtmlCourse> savedHtmlCourses = new ArrayList<>();
            for (HtmlCourseDto htmlCourseDto : htmlCourseDtoList) {
                Long userId = htmlCourseDto.getUserId();
                String courseId = htmlCourseDto.getCourseId();
                String chapter = htmlCourseDto.getChapter();

                for (ChapterContents chapterContents : htmlCourseDto.getChapterContents()) {
                    HtmlCourse htmlCourse = new HtmlCourse();
                    htmlCourse.setUserId(userId);
                    htmlCourse.setCourseId(courseId);
                    htmlCourse.setChapter(chapter);
                    htmlCourse.setContent(chapterContents.getContent());
                    htmlCourse.setImage(chapterContents.getImage());
                    htmlCourse.setOrderChanged(chapterContents.getOrderChanged());
                    htmlCourse.setType(chapterContents.getTyped());

                    // Save the htmlCourse object using your repository
                    HtmlCourse savedHtmlCourse = htmlCourseRepository.save(htmlCourse);
                    savedHtmlCourses.add(savedHtmlCourse);
                }
            }
            return ResponseEntity.ok(htmlCourseDtoList);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving HTML courses");
        }
    }

    public ResponseEntity<?> getHtmlCourseByUserId(Long userId, int pageNo, int pageSize) {
        Page<HtmlCourse> htmlCourses = htmlCourseRepository.findCourseByUserId(userId, PageRequest.of(pageNo, pageSize));
        if (!htmlCourses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(htmlCourses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No HTML courses found for the given user ID");
        }
    }

    public ResponseEntity<?> getAllHtmlCourses(int pageNo, int pageSize) {
        Page<HtmlCourse> htmlCourse = htmlCourseRepository.findAll(PageRequest.of(pageNo,pageSize));
        return ResponseEntity.status(HttpStatus.OK).body(htmlCourse);
    }

    public ResponseEntity<?> getHtmlCourseById(String courseId) {
        HtmlCourse htmlCourse = htmlCourseRepository.findCourseByCourseId(courseId);
        if(htmlCourse != null){
            return ResponseEntity.status(HttpStatus.OK).body(htmlCourse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(htmlCourse);
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
