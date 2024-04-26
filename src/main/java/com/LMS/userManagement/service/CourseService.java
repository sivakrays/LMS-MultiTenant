package com.LMS.userManagement.service;
import com.LMS.userManagement.awsS3.AWSS3Service;
import com.LMS.userManagement.dto.CourseDetailDto;
import com.LMS.userManagement.dto.CourseDto;
import com.LMS.userManagement.mapper.CourseMapper;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.records.CourseDTO;
import com.LMS.userManagement.repository.*;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;


import java.util.*;

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
    ChapterRepository chapterRepository;
    @Autowired
    ChapterContentRepository chapterContentRepository;
    @Autowired
    PurchasedCourseRepository purchasedCourseRepository;

    @Autowired
    AWSS3Service awss3Service;

    private final CourseMapper mapper;

    public CourseService(CourseMapper mapper) {
        this.mapper = mapper;
    }

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

    public CommonResponse<List<Course>> deleteCourseById(String courseId) {

        try {
            List<Course> courses = null;
            if (courseRepository.existsById(courseId)) {
                Optional<Course> course = courseRepository.findById(courseId);
                Long userId = course.get().getUserId();
                courseRepository.deleteById(courseId);
                 courses = courseRepository.findByUserId(userId);
                return CommonResponse.<List<Course>>builder()
                        .status(true)
                        .message(Constant.DELETE_COURSE)
                        .data(courses)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                return CommonResponse.<List<Course>>builder()
                        .status(false)
                        .message(Constant.NO_COURSE)
                        .data(courses)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

        } catch (Exception e) {
            return CommonResponse.<List<Course>>builder()
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



    public CommonResponse<Course> saveCourse(Course course, MultipartFile file) {
        String key="LmsCourse/thumbNail/"+ UUID.randomUUID().toString();

        if (file == null || !file.getContentType().startsWith("image")){
            return CommonResponse.<Course>builder()
                    .status(false)
                    .message(Constant.IMAGE_NOT_SUPPORTED)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .build();
        }


        try {
         String thumbNailUrl=   awss3Service.uploadFile(file,key);
            course.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            course.setThumbNail(thumbNailUrl);
            Course   savedCourse = courseRepository.save(course);
            return CommonResponse.<Course>builder()
                    .status(true)
                    .data(savedCourse)
                    .message(Constant.COURSE_SAVED)
                    .statusCode(Constant.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<Course>builder()
                    .status(false)
                    .message(Constant.COURSE_SAVE_FAILED)
                    .statusCode(Constant.FORBIDDEN)
                    .error(e.getMessage())
                    .build();
        }
    }


    public CommonResponse<CourseDTO> getCourseById(String courseId,Long userId) {


            Course  courseDetails = courseRepository.findByCourseId(courseId);

            if(courseDetails != null){
                //userId of the person who published the course
                String profileImage=courseRepository.findUserProfileByUserId(courseDetails.getUserId());
                Boolean purchased=  purchasedCourseRepository.findByCourseIdAndUserId(courseId,userId);
                if (purchased==null){
                    purchased=false;
                }
                CourseDTO courseDTO=mapper.CourseToCourseDtoMapper(courseDetails,profileImage,purchased);

                return CommonResponse.<CourseDTO>builder()
                        .status(true)
                        .data(courseDTO)
                        .message(Constant.COURSES_FOUND)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                return CommonResponse.<CourseDTO>builder()
                        .status(false)
                        .message(Constant.NO_COURSE)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

    }


    public CommonResponse<LinkedList<CourseDto>> getAllCourses(Long userId) {

        //not by siva
      //  List<CourseDetailDto> courseList=  courseRepository.findAllCourseDetailsByUserId(userId);
        // added by siva
        LinkedList<CourseDto> courseDtoList=new LinkedList<>();

        List<CourseDetailDto> courseList = courseRepository.findAllCourseDetails();
        if (courseList.isEmpty()){
               return CommonResponse.<LinkedList<CourseDto>>builder()
                    .status(false)
                    .data(courseDtoList)
                    .message(Constant.NO_COURSE)
                    .statusCode(Constant.NO_CONTENT)
                    .build();
        }
       courseList.forEach(course -> {
            Boolean purchased = purchasedCourseRepository
                    .findByCourseIdAndUserId(course.getCourse_id(),userId);
            if (purchased == null) {
                purchased = false;
            }

           CourseDto c=       CourseDto.builder()
                   .isHtmlCourse(course.getIs_html_course())
                   .courseId(course.getCourse_id())
                   .price(course.getPrice())
                   .category(course.getCategory())
                   .title(course.getTitle())
                   .createdDate(course.getCreated_date())
                   .isFree(course.getIs_free())
                   .isPurchased(purchased)
                   .ratings(course.getRatings())
                   .language(course.getLanguage())
                   .authorName(course.getAuthor_name())
                   .thumbNail(course.getThumb_nail())
                   .profileImage(course.getProfile_image())
                   .userId(course.getUser_id())
                   .build();
           courseDtoList.add(c);
        });

        return CommonResponse.<LinkedList<CourseDto>>builder()
                .status(true)
                .data(courseDtoList)
                .message(Constant.COURSES_FOUND)
                .statusCode(Constant.SUCCESS)
                .build();
            }




    public CommonResponse<List<CourseDetailDto>> searchCourses(String search) {
      List<CourseDetailDto>   coursesList = courseRepository.searchAllCourse(search);

        if (search.isEmpty()) {
                // Return an empty list if the search string is empty
                return CommonResponse.<List<CourseDetailDto> >builder()
                        .status(false)
                        .data(new ArrayList<>())
                        .message(Constant.NO_DATA)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

            if (!coursesList.isEmpty()) {
                // Return courses if found
                return CommonResponse.<List<CourseDetailDto>>builder()
                        .status(true)
                        .data(coursesList)
                        .message(Constant.COURSES_FOUND)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                // Return an empty list if no courses found
                return CommonResponse.<List<CourseDetailDto> >builder()
                        .status(false)
                        .data(coursesList)
                        .message(Constant.NO_DATA)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }


    }


    public CommonResponse<List<Course>> getCourseByUserId(Long userId) {

        List<Course> courses = null;
        try {
            courses = courseRepository.findByUserId(userId);
            if (!courses.isEmpty()) {
                return CommonResponse.<List<Course>>builder()
                        .status(true)
                        .data(courses)
                        .message(Constant.COURSES_FOUND)
                        .statusCode(Constant.SUCCESS)
                        .build();
            } else {
                return CommonResponse.<List<Course>>builder()
                        .status(false)
                        .data(courses)
                        .message(Constant.NO_COURSE)
                        .statusCode(Constant.NO_CONTENT)
                        .build();
            }

        } catch (Exception e) {
            return CommonResponse.<List<Course>>builder()
                    .status(false)
                    .data(courses)
                    .message(Constant.FAILED_COURSES_FETCH)
                    .statusCode(Constant.FORBIDDEN)
                    .build();
        }
    }
    public CommonResponse<List<Chapter>> saveHtmlCourse(List<Chapter> chapterList) {
        List<Chapter> chapters;
       try {
         /*  chapterList.sort(Comparator.comparingInt(Chapter::getChapterOrder));
           chapterList.forEach(chapter -> {
               chapter.getChapterContent()
                       .sort(Comparator.comparingInt(ChapterContent::getChapterContentOrder));
           });*/
            chapters = chapterRepository.saveAll(chapterList);
           return CommonResponse.<List<Chapter>>builder()
                   .status(true)
                   .statusCode(Constant.SUCCESS)
                   .message(Constant.SAVE_HTML_COURSE)
                   .data(chapters)
                   .build();
       }catch (Exception e){
           return CommonResponse.<List<Chapter>>builder()
                   .status(false)
                   .statusCode(Constant.INTERNAL_SERVER_ERROR)
                   .message(Constant.FAILED_SAVE_HTML_COURSE)
                   .data(new ArrayList<>())
                   .build();
       }
    }
    public CommonResponse<Chapter> updateChapter(Chapter chapter) {
        Chapter updatedChapter = null;
        try{
            updatedChapter=new Chapter();
        Optional<Chapter> existingChapterOptional = chapterRepository.findById(chapter.getChapterId());
        if (existingChapterOptional.isEmpty()) {
            return CommonResponse.<Chapter>builder()
                    .status(false)
                    .statusCode(Constant.NO_CONTENT)
                    .message(Constant.CHAPTER_NOT_FOUND)
                    .data(updatedChapter)
                    .build();
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chapter Not Found");
        }

        // Get the existing chapter
        Chapter existingChapter = existingChapterOptional.get();

        // Update fields of the existing chapter with values provided in the request
            //commented bby siva
      ///  if (chapter.getUserId() != null) {
            existingChapter.setUserId(chapter.getUserId());
   //     }
        if (chapter.getHtml_course_id() != null) {
            existingChapter.setHtml_course_id(chapter.getHtml_course_id());
        }
        if (chapter.getChapter() != null) {
            existingChapter.setChapter(chapter.getChapter());
        }
        if (chapter.getChapterOrder() != null) {
            existingChapter.setChapterOrder(chapter.getChapterOrder());
        }
        if (chapter.getChapterContent() != null) {
            List<ChapterContent> existingChapterContent = existingChapter.getChapterContent();
            List<ChapterContent> updatedChapterContent = chapter.getChapterContent();
            if (existingChapterContent != null && updatedChapterContent != null) {
                for (int i = 0; i < Math.min(existingChapterContent.size(), updatedChapterContent.size()); i++) {
                    ChapterContent existingContent = existingChapterContent.get(i);
                    ChapterContent updatedContent = updatedChapterContent.get(i);
                    if (updatedContent != null) {
                        if (updatedContent.getChapterContentOrder() != null) {
                            existingContent.setChapterContentOrder(updatedContent.getChapterContentOrder());
                        }
                        if (updatedContent.getContent() != null) {
                            existingContent.setContent(updatedContent.getContent());
                        }
                        if (updatedContent.getImage() != null) {
                            existingContent.setImage(updatedContent.getImage());
                        }
                        if (updatedContent.getOrderChanged() != null) {
                            existingContent.setOrderChanged(updatedContent.getOrderChanged());
                        }
                        if (updatedContent.getType() != null) {
                            existingContent.setType(updatedContent.getType());
                        }
                    }
                }
            }
        }

        // Save the updated chapter in the database
       updatedChapter= chapterRepository.save(existingChapter);
            return CommonResponse.<Chapter>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.UPDATED_CHAPTER)
                    .data(updatedChapter)
                    .build();
        }catch (Exception e){
            return CommonResponse.<Chapter>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_UPDATE_CHAPTER)
                    .data(updatedChapter)
                    .build();
        }


    }

    public CommonResponse<ChapterContent> updateChapterContent(ChapterContent chapterContent) {
        ChapterContent updatedChapterContent = null;
        try {
            updatedChapterContent = new ChapterContent();
            Optional<ChapterContent> existingChapterContentOptional = chapterContentRepository.findById(chapterContent.getChapterContentId());
            if (existingChapterContentOptional.isEmpty()) {
                return CommonResponse.<ChapterContent>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.CHAPTER_CONTENT_NOT_FOUND)
                        .data(updatedChapterContent)
                        .build();
            }
            ChapterContent existingChapterContent = existingChapterContentOptional.get();

            // Update fields only if they are not null in the incoming chapterContent
            if (chapterContent.getChapterContentId() != null) {
                existingChapterContent.setChapterContentId(chapterContent.getChapterContentId());
            }
            if (chapterContent.getChapterContentOrder() != null) {
                existingChapterContent.setChapterContentOrder(chapterContent.getChapterContentOrder());
            }
            if (chapterContent.getContent() != null) {
                existingChapterContent.setContent(chapterContent.getContent());
            }
            if (chapterContent.getOrderChanged() != null) {
                existingChapterContent.setOrderChanged(chapterContent.getOrderChanged());
            }
            if (chapterContent.getType() != null) {
                existingChapterContent.setType(chapterContent.getType());
            }
            if (chapterContent.getImage() != null) {
                existingChapterContent.setImage(chapterContent.getImage());
            }

            updatedChapterContent = chapterContentRepository.save(existingChapterContent);
            return CommonResponse.<ChapterContent>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.UPDATED_CHAPTER_CONTENT)
                    .data(updatedChapterContent)
                    .build();
        }catch (Exception e){
            return CommonResponse.<ChapterContent>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_UPDATE_CHAPTER_CONTENT)
                    .data(updatedChapterContent)
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