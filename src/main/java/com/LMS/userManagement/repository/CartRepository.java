package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
   List<Cart>  findByUserId(Long userId);

   Cart findByCourseIdAndUserId(UUID courseId, Long userId);
   @Query(value = "SELECT COUNT(cart_id) FROM cart WHERE user_id =?1", nativeQuery = true)
   int cartCountByUserId(@Param("userId") Long userId);
}
