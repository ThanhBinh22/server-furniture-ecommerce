package com.thesis.serverfurnitureecommerce.internal.services.email;

public interface IEmailService {
    public void sendMailOTP(String to, Integer otp);

    public void sendMailForgotPassword(String to, String resetLink);
}
