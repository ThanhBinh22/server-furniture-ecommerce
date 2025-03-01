package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.thesis.serverfurnitureecommerce.domain.request.OrderRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.PaymentResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.internal.services.paypal.PayPalService;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.CurrencyConverterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    PayPalService payPalService;
    private final RestClient.Builder builder;
    CurrencyConverterService   currencyConverterService;


    @PostMapping("/checkout")
    public ResponseEntity<APIResponse<PaymentResponse>> checkout(@RequestBody OrderRequest orderRequest) throws PayPalRESTException {
        log.info("POST /api/payment/checkout");
        String cancelUrl = "http://localhost:5173/payment/cancel";
        String successUrl = "http://localhost:5173/payment/success";

        String paymentMethod = orderRequest.getPayment();
        Payment payment = null;
        PaymentResponse response = null;
        if ("PAYPAL".equals(paymentMethod)) {
            payment = payPalService.createPayment(currencyConverterService.convertVNDToUSD(orderRequest.getTotalAmount()), "USD", "paypal", "sale",
                    "payment description", cancelUrl, successUrl
            );
            response = PaymentResponse.builder().orderID(1 * 1l).paymentMethod(paymentMethod).build();
            for (com.paypal.api.payments.Links link : payment.getLinks()) {
                if ("approval_url".equals(link.getRel())) {
                    String approvalUrl = link.getHref();
                    response.setUrlPayment(approvalUrl);
                    log.info("Approval URL: {}", approvalUrl);
                }
            }
        }
        return ResponseBuilder.buildResponse(response, ErrorCode.SUCCESS);
    }

    @GetMapping("/success")
    public ResponseEntity<APIResponse<String>> success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws PayPalRESTException {
        log.info("GET /api/payment/success");
        payPalService.executePayment(paymentId, payerId);
        return ResponseBuilder.buildResponse("Payment success", ErrorCode.SUCCESS);
    }

    @GetMapping("/cancel")
    public ResponseEntity<APIResponse<String>> cancel() {
        log.info("GET /api/payment/cancel");
        return ResponseBuilder.buildResponse("Payment cancel", ErrorCode.SUCCESS);
    }
}
