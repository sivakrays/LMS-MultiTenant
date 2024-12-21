package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.PurchasedCourseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedCourseSectionRepository extends JpaRepository<PurchasedCourseSection,Long> {

    @Query("SELECT p.sectionId FROM PurchasedCourseSection p " +
            "WHERE p.userId = :userId AND p.purchasedCourseId = :purchasedCourseId")
    List<String> findSectionIdsByUserIdAndPurchasedCourseId(@Param("userId") long userId, @Param("purchasedCourseId") Long purchasedCourseId);

}
