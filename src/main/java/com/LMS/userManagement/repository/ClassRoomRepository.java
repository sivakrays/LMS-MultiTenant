package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom,Long> {

    List<ClassRoom> findByCreatedBy(Long createdBy);

}
