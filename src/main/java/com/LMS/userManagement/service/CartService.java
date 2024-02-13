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
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CartRepository cartRepository;


    public ResponseEntity<?> saveCart(Cart cart) {
        Long userId = cart.getUserId();
        UUID courseId = cart.getCourseId();
    List<Cart> cartList = cartRepository.findByUserId(userId);
    if(!cartList.isEmpty()) {
        Cart cart1 = cartRepository.findByCourseIdAndUserId(courseId,userId);
        if (cart1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Course already exists");
        }
    }
                cartRepository.save(cart);
    List<Cart> carts =cartRepository.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(carts);


    }

    public ResponseEntity<?> getCartDetailByUserId(Long userId) {
        List<CartDetail> cartDetails = new ArrayList<>();
      List<Cart> cart= cartRepository.findByUserId(userId);
      if(!cart.isEmpty()){
      for(Cart cart1 : cart) {
          UUID courseId = cart1.getCourseId();
          Course course = courseRepository.findCourseByCourseId(courseId);
        if(course != null) {
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

      }
          return ResponseEntity.status(HttpStatus.OK).body(cartDetails);
      }
      return ResponseEntity.status(HttpStatus.OK).body(cartDetails);


    }


    public ResponseEntity<?> deleteCartById(UUID cartId) {
        if (cartRepository.existsById(cartId)){
       Optional<Cart> cart = cartRepository.findById(cartId);
        Long userId = cart.get().getUserId();
        cartRepository.deleteById(cartId);
        List<Cart> cartList = cartRepository.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cartList);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cart not found");
    }
}
