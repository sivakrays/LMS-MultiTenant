package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom,String> {

    List<ClassRoom> findByCreatedBy(Long createdBy);

  /*  @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM ClassRoom c WHERE :userId MEMBER OF c.userIds")
    boolean existsByUserIdsContaining(@Param("userId") Long userId);

    @Query(value = "SELECT cr.classroomId FROM ClassRoom cr WHERE :userId MEMBER OF cr.userIds")
    List<String> findClassroomIdsByUserId(@Param("userId") Long userId);*/

}
