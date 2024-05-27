package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.CartDetail;
import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.repository.CartRepository;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CartRepository cartRepository;


    public CommonResponse<List<Cart>> saveCart(Cart cart) {

            long userId = cart.getUserId();
            String courseId = cart.getCourseId();
            cart.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Optional<Cart> existingCart = cartRepository.findByCourseIdAndUserId(courseId, userId);
        if(existingCart.isEmpty()) {
            cartRepository.save(cart);
            List<Cart>  cartList = cartRepository.findByUserId(userId);
            return CommonResponse.<List<Cart>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.CART_SAVED)
                    .data(cartList)
                    .build();
        }
        List<Cart>  cartList = cartRepository.findByUserId(userId);
        return CommonResponse.<List<Cart>>builder()
                .status(false)
                .statusCode(Constant.SUCCESS)
                .message(Constant.COURSE_EXISTS_CART)
                .data(cartList)
                .build();

    }


    public CommonResponse<List<CartDetail>> getCartDetailByUserId(Long userId) {
        List<CartDetail> cartDetails = new ArrayList<>();
            List<Cart> carts = cartRepository.findByUserId(userId);




            if (!carts.isEmpty()) {
                for (Cart cart : carts) {
                    String courseId = cart.getCourseId();
                    Course course = courseRepository.findByCourseId(courseId);

                    if (course != null) {
                        CartDetail cartDetail = new CartDetail();
                        cartDetail.setCartId(cart.getCartId());
                        cartDetail.setCourseId(course.getCourseId());
                        cartDetail.setTitle(course.getTitle());
                        cartDetail.setCategory(course.getCategory());
                        cartDetail.setAuthorName(course.getAuthorName());
                        cartDetail.setThumbNail(course.getThumbNail());
                        cartDetail.setPrice(course.getPrice());
                        cartDetails.add(cartDetail);
                    }
                }
                return CommonResponse.<List<CartDetail>>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message(Constant.CART_DETAILS_FOUND)
                        .data(cartDetails)
                        .build();
            }
                return CommonResponse.<List<CartDetail>>builder()
                        .status(false)
                        .statusCode(Constant.NO_CONTENT)
                        .message(Constant.EMPTY_CART)
                        .data(cartDetails)
                        .build();

    }



    public CommonResponse<List<CartDetail>> deleteCartById(String cartId) {
        List<CartDetail> cartDetails = null;
        try {
            cartDetails = new ArrayList<>();
            if (cartRepository.existsById(cartId)) {
                Optional<Cart> cart = cartRepository.findById(cartId);
                Long userId = cart.get().getUserId();
                cartRepository.deleteById(cartId);
                List<Cart> cartList = cartRepository.findByUserId(userId);

                if (!cartList.isEmpty()) {
                    for (Cart cart1 : cartList) {
                        String courseId = cart1.getCourseId();
                        Course course = courseRepository.findByCourseId(courseId);

                        if (course != null) {
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
                    return CommonResponse.<List<CartDetail>>builder()
                            .status(true)
                            .statusCode(Constant.SUCCESS)
                            .message(Constant.CART_DELETED)
                            .data(cartDetails)
                            .build();
                }
                    return CommonResponse.<List<CartDetail>>builder()
                            .status(false)
                            .statusCode(Constant.SUCCESS)
                            .message(Constant.CART_EMPTY_DELETION)
                            .data(cartDetails)
                            .build();

            }
            return CommonResponse.<List<CartDetail>>builder()
                    .status(false)
                    .statusCode(Constant.NO_CONTENT)
                    .message(Constant.NO_DATA)
                    .data(cartDetails)
                    .build();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return CommonResponse.<List<CartDetail>>builder()
                    .status(false)
                    .statusCode(Constant.INTERNAL_SERVER_ERROR)
                    .message(Constant.FAILED_DELETE_CART)
                    .data(cartDetails)
                    .build();
        }
    }
    @Transactional
    public CommonResponse<List<Cart>> deleteCartByUserId(Long userId) {
        List<Cart> cartList = null;
        try{
            cartList = new ArrayList<>();
            cartRepository.deleteAllByUserId(userId);
            cartList = cartRepository.findByUserId(userId);
            return CommonResponse.<List<Cart>>builder()
                    .status(true)
                    .statusCode(Constant.SUCCESS)
                    .message(Constant.CART_DELETED)
                    .data(cartList)
                    .build();

        } catch (Exception e) {
        // Log the exception or handle it appropriately
       return CommonResponse.<List<Cart>>builder()
               .status(false)
               .statusCode(Constant.INTERNAL_SERVER_ERROR)
               .message(Constant.FAILED_DELETE_CART)
               .data(cartList)
               .build();

    }

    }
    }