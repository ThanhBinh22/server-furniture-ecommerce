package com.thesis.serverfurnitureecommerce.internal.services.email;

public interface EmailService {
    public void sendMailOTP(String to, Integer otp);

    public void sendMailForgotPassword(String to, Integer otp);
}
