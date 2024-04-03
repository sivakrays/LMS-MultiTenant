package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {
    public String cartId;
    public String courseId;
    public String title;
    public String authorName;
    public String thumbNail;
    public String category;
    private int price;
}
