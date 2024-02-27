package com.LMS.userManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {
    @Schema(description = "cartId",example = "08a56153-2d7f-46a6-8e03-a1d94fbb5ee6")
    public UUID cartId;
    @Schema(description = "courseId",example = "a95410fb-673a-436e-82da-6ed07a43dcee")
    public UUID courseId;
    @Schema(description = "title",example = "Personal finance")
    public String title;
    @Schema(description = "authorName",example = "Steeve simbert")
    public String authorName;
    @Schema(description = "thumbNail",example = "data:image/jpeg;base64,/9j/")
    public String thumbNail;
    @Schema(description = "category",example = "Finance")
    public String category;
    @Schema(description = "price",example = "399")
    private Integer price;
}
