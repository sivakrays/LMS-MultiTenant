package com.LMS.userManagement.service;
import com.LMS.userManagement.dto.CourseDto;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    private ChapterRepository chapterRepository;

    @Autowired PurchasedCourseRepository purchasedCourseRepository;

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
        course.setDate(new Timestamp(System.currentTimeMillis()));
        Course courseDetails = courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).body(courseDetails);

    }

    public ResponseEntity<?> getCourseById(String courseId,Long userId) {
        Course course = courseRepository.findByCourseId(courseId);
        if(course!=null){
          Boolean purchased=  purchasedCourseRepository.findByCourseIdAndUserId(courseId,userId);
          if (purchased==null){
              purchased=false;
          }
                return ResponseEntity.ok( CourseDto.builder()
                        .courseId(course.getCourseId())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .authorName(course.getAuthorName())
                        .thumbNail(course.getThumbNail())
                        .category(course.getCategory())
                        .enrolled(course.getEnrolled())
                        .ratings(course.getRatings())
                        .language(course.getLanguage())
                        .overview(course.getOverview())
                        .whatYouWillLearn(course.getWhatYouWillLearn())
                        .price(course.getPrice())
                        .date(course.getDate())
                        .userId(course.getUserId())
                        .isHtmlCourse(course.getIsHtmlCourse())
                        .isPurchased(purchased)
                        .isFree(course.getIsFree())
                        .chapters(course.getChapters())
                        .sections(course.getSections())
                        .build());

        }
            return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    public ResponseEntity<?> getAllCourses(int pageNo, int pageSize,Long userId) {
     Pageable pageRequest=   PageRequest.of(pageNo, pageSize);
        Page<Course> courseDetails = courseRepository.findAll(pageRequest);

        if (courseDetails.getContent().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(courseDetails);
        }



        List<Course> courseList= courseDetails.getContent();
        List<CourseDto> courseDtoList=new ArrayList<>();
        courseList.forEach(course -> {
                    Boolean purchased = purchasedCourseRepository
                            .findByCourseIdAndUserId(course.getCourseId(),userId);
                    if (purchased == null) {
                        purchased = false;
                    }
                     CourseDto courseDto=   CourseDto.builder()
                                .courseId(course.getCourseId())
                                .title(course.getTitle())
                                .description(course.getDescription())
                                .authorName(course.getAuthorName())
                                .thumbNail(course.getThumbNail())
                                .category(course.getCategory())
                                .enrolled(course.getEnrolled())
                                .ratings(course.getRatings())
                                .language(course.getLanguage())
                                .overview(course.getOverview())
                                .whatYouWillLearn(course.getWhatYouWillLearn())
                                .price(course.getPrice())
                                .date(course.getDate())
                                .userId(course.getUserId())
                                .isHtmlCourse(course.getIsHtmlCourse())
                                .isPurchased(purchased)
                                .isFree(course.getIsFree())
                                .chapters(course.getChapters())
                                .sections(course.getSections())
                                .build();
            courseDtoList.add(courseDto);


        });
        int start= (int) pageRequest.getOffset();
        int end =Math.min(start+pageRequest.getPageSize(),courseDtoList.size());
        List<CourseDto> pageContent=courseDtoList.subList(start,end);

        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(pageContent,pageRequest,courseDtoList.size()));

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

    public ResponseEntity<?> getCourseByUserId(Long userId, int pageNo, int pageSize) {
        Page<Course> courses = courseRepository.findCourseByUserId(userId, PageRequest.of(pageNo, pageSize));

        if (!courses.isEmpty()) {
            courses.forEach(course -> {
                // Check if the course has chapters
                if (course.getChapters() != null && !course.getChapters().isEmpty()) {
                    // Sort chapters and their content within each course
                    course.getChapters().sort(Comparator.comparingInt(Chapter::getChapterOrder));
                    course.getChapters().forEach(chapter -> {
                        if (chapter.getChapterContent() != null && !chapter.getChapterContent().isEmpty()) {
                            chapter.getChapterContent().sort(Comparator.comparingInt(ChapterContent::getChapterContentOrder));
                        }
                    });
                }
            });
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }
    }

    public ResponseEntity<?> saveHtmlCourse(List<Chapter> chapterList) {
        chapterList.sort(Comparator.comparingInt(Chapter::getChapterOrder));
        chapterList.forEach(chapter -> {
            chapter.getChapterContent()
                    .sort(Comparator.comparingInt(ChapterContent::getChapterContentOrder));
        });
        var chapters= chapterRepository.saveAll(chapterList);
        return ResponseEntity.ok(chapters);
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
