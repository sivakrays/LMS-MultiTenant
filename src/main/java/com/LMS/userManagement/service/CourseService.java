package com.LMS.userManagement.service;
import com.LMS.userManagement.dto.CourseData;
import com.LMS.userManagement.dto.CourseDetails;
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

    public ResponseEntity<?> deleteCourseById(String courseId,int pageNo,int pageSize) {

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

    public ResponseEntity<?> getCourseById(String courseId) {
        Course course = courseRepository.findByCourseId(courseId);
        if(course != null){
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
            return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    public ResponseEntity<?> getAllCourses(int pageNo, int pageSize) {
        //Page<Course> course = courseRepository.findAll(PageRequest.of(pageNo, pageSize));
        List<Course> course=courseRepository.findAll();
        CourseData courseData = new CourseData();
        CourseDetails courseDetails = new CourseDetails();
        List<CourseDetails> courseDetailsList =new ArrayList<>();
        List<HtmlCourse> htmlCourseList = new ArrayList<>();
        for (Course course1 : course) {
            String courseId = course1.getCourseId();
            Boolean htmlCourse = course1.isHtmlCourse();
            if (htmlCourse.equals(true)) {
                List<HtmlCourse> htmlCourse1 = htmlCourseRepository.findAllByCourseId(String.valueOf(courseId));
                for (HtmlCourse htmlCourse2 : htmlCourse1) {
                    HtmlCourse chapterContents = new HtmlCourse();
                    chapterContents.setId(htmlCourse2.getId());
                    chapterContents.setHtml_course_id(htmlCourse2.getHtml_course_id());
                    chapterContents.setUserId(htmlCourse2.getUserId());
                    chapterContents.setContent(htmlCourse2.getContent());
                    chapterContents.setChapter(htmlCourse2.getChapter());
                    chapterContents.setContent(htmlCourse2.getContent());
                    chapterContents.setImage(htmlCourse2.getImage());
                    chapterContents.setOrderChanged(htmlCourse2.isOrderChanged());
                    chapterContents.setType(htmlCourse2.getType());
                    htmlCourseList.add(chapterContents);
                }
               courseDetails.setCourseData(course1);
                courseDetails.setHtmlData(htmlCourseList);

            }else{
                courseDetails.setCourseData(course1);

            }
            courseDetailsList.add(courseDetails);
            courseData.setCourseDetails(courseDetailsList);

        }
        return ResponseEntity.status(HttpStatus.OK).body(courseData);

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
                //Long userId = htmlCourseDto.getUserId();
                String courseId = htmlCourseDto.getCourseId();
                String chapter = htmlCourseDto.getChapter();
                Integer chapterOrder=htmlCourseDto.getChapterOrder();
                 long userId=htmlCourseDto.getUserId();

                for (ChapterContents chapterContents : htmlCourseDto.getChapterContents()) {
                    HtmlCourse htmlCourse = new HtmlCourse();
                    //htmlCourse.setUserId(userId);
                    htmlCourse.setHtml_course_id(courseId);
                    htmlCourse.setChapter(chapter);
                    htmlCourse.setChapterOrder(chapterOrder);
                    htmlCourse.setUserId(userId);
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
        Page<HtmlCourse> htmlCourses = htmlCourseRepository.findByUserId(userId, PageRequest.of(pageNo, pageSize));
        if (!htmlCourses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(htmlCourses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No HTML courses found for the given user ID");
        }
    }

    public ResponseEntity<?> getAllHtmlCourses(int pageNo, int pageSize) {
        Page<Course> htmlCourse = courseRepository.findAll(PageRequest.of(pageNo,pageSize));
        return ResponseEntity.status(HttpStatus.OK).body(htmlCourse);
    }

    public ResponseEntity<?> getHtmlCourseById(String courseId) {
        Course htmlCourse = courseRepository.findByCourseId(courseId);
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
