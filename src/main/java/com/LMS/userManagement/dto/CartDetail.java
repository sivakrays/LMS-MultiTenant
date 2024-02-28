package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {
    public UUID cartId;
    public UUID courseId;
    public String title;
    public String authorName;
    public String thumbNail;
    public String category;
    private Integer price;
}
