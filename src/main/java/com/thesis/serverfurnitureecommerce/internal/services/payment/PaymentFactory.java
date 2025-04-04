package com.thesis.serverfurnitureecommerce.internal.services.payment;

public class PaymentFactory {
    public static PaymentService getPaymentService(String type) {
        return switch (type) {
            case "CREDIT_CARD" -> new CreditCardServiceImpl();
            case "CASH" -> new CashServiceImpl();
            case "PAYPAL" -> new PaypalServiceImpl();
            default -> throw new IllegalArgumentException("Invalid payment type");
        };
    }
}
