package com.LMS.userManagement.Razorpay;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigInteger;
@Data
public class OrderRequest {

        String customerName;
        String email;
        String phoneNumber;
        Float amount;

}
