package com.LMS.userManagement.swaggerResponse;

import com.LMS.userManagement.dto.ProfileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProfileResponse {
    @Schema(description = "status",example = "true")
    public boolean status;
    @Schema(description = "statusCode",example = "200")
    public int statusCode;
    @Schema(description = "message",example = "success(Can be modified by as per api)")
    public String message;
    public ProfileDto data;
    @Schema(description = "error",example = "null")
    public String error;
}
