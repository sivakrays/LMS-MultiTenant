package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.PurchasedCourseSubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedCourseSubSectionRepository extends JpaRepository<PurchasedCourseSubSection,Long> {

    @Query("SELECT p.subSectionId FROM PurchasedCourseSubSection p " +
            "WHERE p.userId = :userId AND p.purchasedCourseId = :purchasedCourseId")
    List<String> findSubSectionIdsByUserIdAndPurchasedCourseId(@Param("userId") long userId, @Param("purchasedCourseId") Long purchasedCourseId);

}
