package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
   List<Cart>  findByUserId(Long userId);

   Cart findByCourseIdAndUserId(Integer courseId, long userId);
}
