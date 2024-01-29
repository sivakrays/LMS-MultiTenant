package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.QuizDto;
import com.LMS.userManagement.dto.SectionDto;
import com.LMS.userManagement.dto.SubSectionDto;
import com.LMS.userManagement.model.Quiz;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.model.SubSection;
import com.LMS.userManagement.repository.QuizRepository;
import com.LMS.userManagement.repository.SectionRepository;
import com.LMS.userManagement.repository.SubSectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    SubSectionRepository subSectionRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    QuizRepository quizRepository;
    @Transactional
    public Quiz updateQuiz(QuizDto quizDto) {
        Optional<Quiz> existingQuiz = quizRepository.findById(quizDto.getSubSectionId(),quizDto.getQuizId());
        Quiz quiz= existingQuiz.get();
        quiz.setKey(quizDto.getKey());
        quiz.setTitle(quizDto.getTitle());
        quiz.setQuestion(quizDto.getQuestion());
        quiz.setQuestion(quizDto.getQuestion());
        quiz.setOptions(quizDto.getOptions());
        quiz.setAnswer(quizDto.getAnswer());
        Quiz quiz1 = quizRepository.save(quiz);
        return quiz1;
    }
    @Transactional
    public SubSection updateSubSection(SubSectionDto subSectionDto) {
        Optional<SubSection> existingSubSection = subSectionRepository.findById(subSectionDto.getSectionId(),subSectionDto.getSubSectionId());
        SubSection subSection = existingSubSection.get();
        subSection.setTitle(subSectionDto.getTitle());
        subSection.setKey(subSectionDto.getKey());
        subSection.setDescription(subSectionDto.getDescription());
        subSection.setLink(subSectionDto.getLink());
        SubSection subSection1 =subSectionRepository.save(subSection);
        return subSection1;

    }

    public Section updateSection(SectionDto sectionDto) {
        Optional<Section> existingSection = sectionRepository.findSectionBySectionIdAndCourseId(sectionDto.getSectionId(),sectionDto.getCourseId());
        Section section = existingSection.get();
        section.setKey(sectionDto.getKey());
        section.setTitle(sectionDto.getTitle());
        Section section1 =sectionRepository.save(section);
        return section1;

    }
}
