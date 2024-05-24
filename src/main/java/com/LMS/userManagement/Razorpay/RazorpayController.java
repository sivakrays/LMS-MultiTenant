package com.LMS.userManagement.Razorpay;

import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/lms/api/auth")
@Tag(name = "Razorpay", description = "Payment management APIs")

public class RazorpayController {

    private RazorpayClient client;
    private static final String SECRET_ID = "rzp_test_9x1DYeY8MIVVTO";
    private static final String SECRET_KEY = "k7QAPSwoA3zwl5deNmNaN2IR";

    @PostMapping("/createOrder")
    public CommonResponse<OrderResponse>  createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = new OrderResponse();
        try {

            client = new RazorpayClient(SECRET_ID, SECRET_KEY);

            Order order = createRazorPayOrder(orderRequest.getAmount());
            System.out.println("---------------------------");
            String orderId = (String) order.get("id");
            System.out.println("Order ID: " + orderId);
            System.out.println("---------------------------");

            response.setRazorpayOrderId(orderId);
            response.setPayment("" + orderRequest.getAmount());

            response.setSecretKey(SECRET_KEY);
            response.setSecretId(SECRET_ID);

            return CommonResponse.<OrderResponse>builder()
                    .status(true)
                    .data(response)
                    .statusCode(Constant.SUCCESS)
                    .message("order success")
                    .build();

        } catch (RazorpayException e) {
            return CommonResponse.<OrderResponse>builder()
                    .status(false)
                    .data(response)
                    .statusCode(Constant.FORBIDDEN)
                    .message("order failed")
                    .error(e.getMessage())
                    .build();
        }


    }

    private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", amount.multiply(new BigInteger("100")));
      //  options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
        return client.orders.create(options);
    }

}
