package com.LMS.userManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    @Schema(description = "userId",example = "1")
    public Long id;
    @Schema(description = "name",example = "Ram")
    public String name;
    @Schema(description = "gender",example = "male")
    public String gender;
    @Schema(description = "school",example = "LMS school")
    public String school;
    @Schema(description = "standard",example = "10")
    public Integer standard;
    @Schema(description = "city",example = "Chennai")
    public String city;
    @Schema(description = "country",example = "India")
    public String country;
}
