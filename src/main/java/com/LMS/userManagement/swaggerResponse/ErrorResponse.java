package com.LMS.userManagement.swaggerResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

@Data
public class ErrorResponse {
    @Schema(description = "status",example = "false")
    public boolean status;
    @Schema(description = "statusCode",example = "403")
    public int statusCode;
    @Schema(description = "error",example = "forbidden")
    private String error;
    @Schema(description = "message",example = "Can be modified by as per api")
    private  String message;
    @Schema(description = "data",example = "null")
    public T data;




}
