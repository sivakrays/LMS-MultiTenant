package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.QuizRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRankRepository extends JpaRepository<QuizRank, String> {

    Optional<QuizRank> findByUserIdAndSectionId(Long userId, String sectionId);

   /* @Query(value = "select badge, count(*) from quiz_rank where user_id=?1 AND badge=?2 group by badge",nativeQuery = true)
    Long countByUserIdAndBadge(@Param("userId") Long userId, @Param("badge") int badge);*/

   /* @Query("SELECT COUNT(*) FROM QuizRank  WHERE userId =?1 AND badge =?2")
    Long countByUserIdAndBadge(@Param("userId") Long userId, @Param("badge") int badge);*/

    @Query(value = "SELECT COUNT(*) FROM quiz_rank WHERE user_id =?1 AND badge =?2", nativeQuery = true)
    int countByUserIdAndBadge(@Param("userId") Long userId, @Param("badge") int badge);

    @Query(value = "SELECT CASE WHEN SUM(energy_points) IS NULL THEN 0 ELSE SUM(energy_points) END AS E_POINTS  FROM quiz_rank where user_id=?1",nativeQuery = true)
    Integer sumOfEnergyPoints(@Param("userId") Long userId);

}
