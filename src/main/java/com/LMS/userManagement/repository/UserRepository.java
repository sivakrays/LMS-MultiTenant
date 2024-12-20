package com.LMS.userManagement.repository;

import com.LMS.userManagement.dto.UserProfileDto;
import com.LMS.userManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String username);

    @Query(value = "SELECT name,email,id,standard,city,country,school,gender,profile_image FROM USER_DETAILS WHERE ID=?1",nativeQuery = true)
    UserProfileDto findUserByUserId(Long userId);

    @Query(value = "SELECT name FROM USER_DETAILS WHERE ID = ?1", nativeQuery = true)
    String findUserNameByUserId(Long userId);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findTheUserByUserId(@Param("id") Long id);

    @Query(value = "SELECT id FROM USER_DETAILS WHERE standard = ?1", nativeQuery = true)
    List<Long> findUserIdsByStandard(Integer standard);


}
