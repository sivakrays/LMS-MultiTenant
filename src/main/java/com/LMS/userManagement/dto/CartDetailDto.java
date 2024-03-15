package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {

    public String cartId;
    public String courseId;
    public String title;
    public String authorName;
    public String thumbNail;
    public String category;
    private Integer price;
}
