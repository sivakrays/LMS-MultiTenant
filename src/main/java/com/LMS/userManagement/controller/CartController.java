package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Tag(name = "Cart", description = "Cart management APIs")

public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/saveCart")
    public CommonResponse<?> saveCart(@RequestBody Cart cart){
        return cartService.saveCart(cart);
    }

    @GetMapping("/getCartByUserId")
    public CommonResponse<?> getCartDetailByUserId(@RequestParam Long userId){
        return cartService.getCartDetailByUserId(userId);
    }
    @DeleteMapping("/deleteCartById")
    public CommonResponse<?> deleteCartById(@RequestParam UUID cartId){
        return cartService.deleteCartById(cartId);
    }
}
