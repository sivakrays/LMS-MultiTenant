package com.LMS.userManagement.swaggerResponse;

import com.LMS.userManagement.model.Cart;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class SaveCartResponse {
    @Schema(description = "status",example = "true")
    public boolean status;
    @Schema(description = "statusCode",example = "200")
    public int statusCode;
    @Schema(description = "message",example = "success(Can be modified by as per api)")
    public String message;
    public Cart data;
    @Schema(description = "error",example = "null")
    public String error;
}
