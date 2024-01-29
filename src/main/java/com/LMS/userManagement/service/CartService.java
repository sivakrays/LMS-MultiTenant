package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.CartDetail;
import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.repository.CartRepository;
import com.LMS.userManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CartRepository cartRepository;


    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public ResponseEntity<?> getCartDetailByUserId(Long userId) {
        List<CartDetail> cartDetails = new ArrayList<>();
      List<Cart> cart= cartRepository.findByUserId(userId);
      for(Cart cart1 : cart) {
          Course course = courseRepository.findCourseByCourseId(cart1.getCourseId());

          CartDetail cartDetail = new CartDetail();
          cartDetail.setCourseId(course.getCourseId());
          cartDetail.setTitle(course.getTitle());
          cartDetail.setCategory(course.getCategory());
          cartDetail.setAuthorName(course.getAuthorName());
          cartDetail.setThumbNail(course.getThumbNail());
          cartDetail.setPrice(course.getPrice());

          cartDetails.add(cartDetail);
      }

       return ResponseEntity.ok(cartDetails);


    }


    public ResponseEntity<?> deleteCartById(Long cartId) {
        if (cartRepository.existsById(cartId)){
            cartRepository.deleteById(cartId);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("Cart not found");
    }
}
