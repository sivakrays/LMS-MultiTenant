package com.LMS.userManagement.service;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.Quiz;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.model.SubSection;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.QuizRepository;
import com.LMS.userManagement.repository.SectionRepository;
import com.LMS.userManagement.repository.SubSectionRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    public CommonResponse<List<Section>> saveSection(List<Section> sections) {

        List<Section> sectionList = null;
        try {
            sectionList = sectionRepository.saveAll(sections);
            return CommonResponse.<List<Section>>builder()
                    .status(true)
                    .data(sectionList)
                    .message(Constant.SECTION_SAVED)
                    .statusCode(Constant.SUCCESS)
                    .build();

        } catch (Exception e) {
            return CommonResponse.<List<Section>>builder()
                    .status(false)
                    .statusCode(Constant.FORBIDDEN)
                    .message(Constant.SECTION_SAVE_FAILED)
                    .data(sectionList)
                    .build();
        }

    }

    public CommonResponse<?> deleteCourseById(UUID courseId,int pageNo,int pageSize) {

        try {
            if (courseRepository.existsById(courseId)) {
                Optional<Course> course = courseRepository.findById(courseId);
                Long userId = course.get().getUserId();
                courseRepository.deleteById(courseId);
                Page<Course> courses = courseRepository.findCourseByUserId(userId,PageRequest.of(pageNo,pageSize));
                return CommonResponse.builder()
                        .status(true)
                        .message(Constant.DELETE_COURSE)
                        .data(courses)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                return CommonResponse.builder()
                        .status(false)
                        .message(Constant.NO_COURSE)
                        .data(null)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

        } catch (Exception e) {
            return CommonResponse.builder()
                    .status(false)
                    .message(Constant.DELETE_COURSE_FAILED)
                    .data(null)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }
    public CommonResponse<Course> updateCourse(Course course) {


        try {
            Course updatedCourse = courseRepository.save(course);
            return CommonResponse.<Course>builder()
                    .status(true)
                    .data(updatedCourse)
                    .message(Constant.COURSE_UPDATED)
                    .statusCode(Constant.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<Course>builder()
                    .status(false)
                    .data(null)
                    .message(Constant.COURSE_UPDATE_FAILED)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }

    public CommonResponse<Section> updateSection(Section section) {

        Section updatedSection = null;
        try {
            updatedSection = sectionRepository.save(section);
            return CommonResponse.<Section>builder()
                    .status(true)
                    .data(updatedSection)
                    .message(Constant.SECTION_UPDATED)
                    .statusCode(Constant.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<Section>builder()
                    .status(false)
                    .data(updatedSection)
                    .message(Constant.SECTION_UPDATE_FAILED)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }

    public CommonResponse<SubSection> updateSubSection(SubSection subSection) {

        SubSection updatedSubSection = null;
        try {
            updatedSubSection = subSectionRepository.save(subSection);
            return CommonResponse.<SubSection>builder()
                    .status(true)
                    .data(updatedSubSection)
                    .message(Constant.SUBSECTION_SAVED)
                    .statusCode(Constant.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<SubSection>builder()
                    .status(false)
                    .data(updatedSubSection)
                    .message(Constant.SUBSECTION_UPDATE_FAILED)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }

    public CommonResponse<Quiz> updateQuiz(Quiz quiz) {

        Quiz updatedQuiz = null;
        try {
            updatedQuiz = quizRepository.save(quiz);
            return CommonResponse.<Quiz>builder()
                    .status(true)
                    .data(updatedQuiz)
                    .message(Constant.QUIZ_UPDATED)
                    .statusCode(Constant.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<Quiz>builder()
                    .status(false)
                    .data(updatedQuiz)
                    .message(Constant.FAILED_QUIZ_UPDATE)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }



    public CommonResponse<Course> saveCourse(Course course) {

        Course savedCourse = null;
        try {
            savedCourse = courseRepository.save(course);
            return CommonResponse.<Course>builder()
                    .status(true)
                    .data(savedCourse)
                    .message(Constant.COURSE_SAVED)
                    .statusCode(Constant.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<Course>builder()
                    .status(false)
                    .data(savedCourse)
                    .message(Constant.COURSE_SAVE_FAILED)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }


    public CommonResponse<Course> getCourseById(UUID courseId) {

        Course course = null;
        try {
            course = courseRepository.findCourseByCourseId(courseId);
            if(course != null){
                return CommonResponse.<Course>builder()
                        .status(true)
                        .data(course)
                        .message(Constant.COURSES_FOUND)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                return CommonResponse.<Course>builder()
                        .status(false)
                        .data(course)
                        .message(Constant.NO_COURSE)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

        } catch (Exception e) {
            return CommonResponse.<Course>builder()
                    .status(false)
                    .data(course)
                    .message(Constant.FAILED_COURSE)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }


    public CommonResponse<Page<Course>> getAllCourses(int pageNo, int pageSize) {

        Page<Course> coursePage = null;
        try {
            coursePage = courseRepository.findAll(PageRequest.of(pageNo, pageSize));
            if (coursePage != null && coursePage.hasContent()) {
                return CommonResponse.<Page<Course>>builder()
                        .status(true)
                        .data(coursePage)
                        .message(Constant.COURSES_FOUND)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                return CommonResponse.<Page<Course>>builder()
                        .status(false)
                        .data(coursePage)
                        .message(Constant.NO_COURSE)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

        } catch (Exception e) {
            return CommonResponse.<Page<Course>>builder()
                    .status(false)
                    .data(coursePage)
                    .message(Constant.FAILED_COURSE)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }

    public CommonResponse<Page<Course>> searchCourses(String search, int pageNo, int pageSize) {

        Page<Course> courses = null;
        try {
            if (search.isEmpty()) {
                // Return an empty list if the search string is empty
                return CommonResponse.<Page<Course>>builder()
                        .status(true)
                        .data(new PageImpl<>(new ArrayList<>()))
                        .message(Constant.EMPTY_SEARCH)
                        .statusCode(Constant.SUCCESS)
                        .build();
            }

            courses = courseRepository.searchAllCourse(search, PageRequest.of(pageNo, pageSize));
            if (courses != null && courses.hasContent()) {
                // Return courses if found
                return CommonResponse.<Page<Course>>builder()
                        .status(true)
                        .data(courses)
                        .message(Constant.COURSES_FOUND)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                // Return an empty list if no courses found
                return CommonResponse.<Page<Course>>builder()
                        .status(false)
                        .data(courses)
                        .message(Constant.NO_COURSE)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<Page<Course>>builder()
                    .status(false)
                    .data(courses)
                    .message(Constant.SEARCH_FAILED)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }


    public CommonResponse<Page<Course>> getCourseByUserId(Long userId, int pageNo, int pageSize) {

        Page<Course> courses = null;
        try {
            courses = courseRepository.findCourseByUserId(userId, PageRequest.of(pageNo, pageSize));
            if (courses != null && courses.hasContent()) {
                return CommonResponse.<Page<Course>>builder()
                        .status(true)
                        .data(courses)
                        .message(Constant.COURSES_FOUND)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                return CommonResponse.<Page<Course>>builder()
                        .status(false)
                        .data(courses)
                        .message(Constant.NO_COURSE)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

        } catch (Exception e) {
            return CommonResponse.<Page<Course>>builder()
                    .status(false)
                    .data(courses)
                    .message(Constant.FAILED_COURSES_FETCH)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
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