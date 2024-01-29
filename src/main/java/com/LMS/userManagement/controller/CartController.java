package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/saveCart")
    public Cart saveCart(@RequestBody Cart cart){
        return cartService.saveCart(cart);
    }

    @GetMapping("/getCartDetailByUserId")
    public ResponseEntity<?> getCartDetailByUserId(@RequestHeader Long userId){
        return cartService.getCartDetailByUserId(userId);
    }
    @DeleteMapping("/deleteCartById")
    public ResponseEntity<?> deleteCartById(@RequestHeader Long cartId){
        return cartService.deleteCartById(cartId);
    }
}
