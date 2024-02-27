package com.LMS.userManagement.controller;

import com.LMS.userManagement.swaggerResponse.CartDetailResponse;
import com.LMS.userManagement.swaggerResponse.ErrorResponse;
import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.service.CartService;
import com.LMS.userManagement.swaggerResponse.SaveCartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Save Cart",
            description = "Add courses in the cart",
            responses = {@ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = SaveCartResponse.class))),
                    @ApiResponse(description = "forbidden", responseCode = "403", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),
            })
    public ResponseEntity<?> saveCart(@RequestBody Cart cart){
        return cartService.saveCart(cart);
    }

    @GetMapping("/getCartByUserId")
    @Operation(
            summary = "getCartByUserId",
            description = "get courses in the cart",
            responses = {@ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartDetailResponse.class))),
                    @ApiResponse(description = "forbidden", responseCode = "403", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),
            })
    public ResponseEntity<?> getCartDetailByUserId(@RequestParam Long userId){
        return cartService.getCartDetailByUserId(userId);
    }
    @DeleteMapping("/deleteCartById")
    @Operation(
            summary = "deleteCartById",
            description = "delete courses in the cart",
            responses = {@ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartDetailResponse.class))),
                    @ApiResponse(description = "forbidden", responseCode = "403", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),
            })
    public ResponseEntity<?> deleteCartById(@RequestParam UUID cartId){
        return cartService.deleteCartById(cartId);
    }
}
