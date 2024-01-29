package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {
    @Query(value = "Select * from quiz where sub_section_id=?1 And quiz_id=?2",nativeQuery = true)
    Optional<Quiz> findById(@Param("subSectionId") Integer subSectionId,@Param("quizId") Integer quizId);
}
