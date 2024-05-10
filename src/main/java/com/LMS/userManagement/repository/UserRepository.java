package com.LMS.userManagement.repository;

import com.LMS.userManagement.dto.UserProfileDto;
import com.LMS.userManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String username);

    @Query(value = "SELECT name,email,id,standard,city,country,school,gender,profile_image FROM USER_DETAILS WHERE ID=?1",nativeQuery = true)
    UserProfileDto findUserByUserId(Long userId);
}
