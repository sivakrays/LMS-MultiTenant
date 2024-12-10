package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.ClassroomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomDataRepository extends JpaRepository<ClassroomData,String> {
    boolean existsByUserId(Long userId);

    @Query(value = "SELECT classroom_id from classroom_data WHERE user_id=?1",nativeQuery = true)
    List<String> findByUserId(Long userId);

    @Query(value = "SELECT user_id FROM classroom_data WHERE classroom_id=?1", nativeQuery = true)
    List<Long> findUserIdsByClassroomId(String classroomId);
}
