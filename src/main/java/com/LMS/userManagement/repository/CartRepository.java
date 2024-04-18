package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
   List<Cart>  findByUserId(Long userId);

  Optional<Cart>  findByCourseIdAndUserId(String courseId, Long userId);


   @Query(value = "SELECT COUNT(cart_id) FROM cart WHERE user_id =?1", nativeQuery = true)
   Integer cartCountByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM cart WHERE user_id = ?1", nativeQuery = true)
    void deleteAllByUserId(Long userId);

    @Query(value="SELECT * FROM cart WHERE user_id=:userId AND course_id IN (:courseIds)",nativeQuery = true)
    List<Cart> findByUserIdAndCourseId(Long userId, List<String> courseIds);
}
