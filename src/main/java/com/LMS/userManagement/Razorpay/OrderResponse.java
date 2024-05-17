package com.LMS.userManagement.Razorpay;

import jakarta.persistence.Entity;
import lombok.Data;
@Data
public class OrderResponse {

        String secretKey;
        String razorpayOrderId;
        String payment;
        String secretId;

}
