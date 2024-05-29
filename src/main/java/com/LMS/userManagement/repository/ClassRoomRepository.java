package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom,String> {

    List<ClassRoom> findByCreatedBy(Long createdBy);

    @Query("SELECT COUNT(cr) > 0 FROM ClassRoom cr WHERE :userId IN (cr.userIds)")
    boolean existsByUserIdsContaining(@Param("userId") Long userId);

    @Query("SELECT cr.classroomId FROM ClassRoom cr WHERE :userId IN (cr.userIds)")
    Optional<UUID> findClassroomIdByUserId(@Param("userId") Long userId);
}


