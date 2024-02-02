package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.CartDetail;
import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.repository.CartRepository;
import com.LMS.userManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CartRepository cartRepository;


    public ResponseEntity<?> saveCart(Cart cart) {
        long userId = cart.getUserId();
        Integer courseId = cart.getCourseId();
    List<Cart> cartList = cartRepository.findByUserId(userId);
    if(cartList !=null && !cartList.isEmpty()) {
        Cart cart1 = cartRepository.findByCourseIdAndUserId(courseId,userId);
        if (cart1 != null) {
            return ResponseEntity.status(409).body("Course already exists");
        }
    }
                cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartRepository.findByUserId(userId));


    }

    public ResponseEntity<?> getCartDetailByUserId(Long userId) {
        List<CartDetail> cartDetails = new ArrayList<>();
      List<Cart> cart= cartRepository.findByUserId(userId);
      if(cart != null){
      for(Cart cart1 : cart) {
          Course course = courseRepository.findCourseByCourseId(cart1.getCourseId());

          CartDetail cartDetail = new CartDetail();
          cartDetail.setCartId(cart1.getCartId());
          cartDetail.setCourseId(course.getCourseId());
          cartDetail.setTitle(course.getTitle());
          cartDetail.setCategory(course.getCategory());
          cartDetail.setAuthorName(course.getAuthorName());
          cartDetail.setThumbNail(course.getThumbNail());
          cartDetail.setPrice(course.getPrice());

          cartDetails.add(cartDetail);
      }

       return ResponseEntity.status(HttpStatus.OK).body(cartDetails);
      }
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found");


    }


    public ResponseEntity<?> deleteCartById(Long cartId) {
        if (cartRepository.existsById(cartId)){
            cartRepository.deleteById(cartId);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cart not found");
    }
}
