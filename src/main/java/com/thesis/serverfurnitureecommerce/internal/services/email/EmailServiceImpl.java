package com.thesis.serverfurnitureecommerce.internal.services.email;

import com.thesis.serverfurnitureecommerce.constant.StringConstant;
import jakarta.annotation.PreDestroy;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    private static final String OTP_SUBJECT = "[XÁC NHẬN OTP TỪ FURNITURE]";
    private static final String FORGOT_PASSWORD_SUBJECT = "[Yêu Cầu Đặt Lại Mật Khẩu]";

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMailOTP(String to, Integer otp) {
        log.info("Sending OTP email to {}", to);
        String content = generateEmailContent(otp, StringConstant.TEMPLATE_EMAIL_VERIFY);
        sendMail(to, OTP_SUBJECT, content);
    }

    @Override
    public void sendMailForgotPassword(String to, String resetLink) {
        log.info("Sending forgot password email to {}", to);
        String content = generateEmailContent(resetLink, "Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng nhấp vào liên kết sau để đặt lại mật khẩu: %s");
        sendMail(to, FORGOT_PASSWORD_SUBJECT, content);
    }

    private String generateEmailContent(Integer otp, String template) {
        validateNotNull(otp, StringConstant.OTP_NUll);
        return String.format(template, otp);
    }

    private String generateEmailContent(String resetLink, String messageTemplate) {
        validateNotNull(resetLink, "Reset link cannot be null");
        return String.format(messageTemplate, resetLink);
    }

    private void validateNotNull(Object value, String errorMessage) {
        if (value == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void sendMail(String to, String subject, String content) {
        log.info("Preparing to send email to {}", to);
        executorService.submit(() -> {
            log.info("Submitting email task to executor");
            try {
                MimeMessage message = createMimeMessage(to, subject, content);
                mailSender.send(message);
                log.info("Email sent successfully to {}", to);
            } catch (MessagingException ex) {
                log.error("Failed to send email to {}", to, ex);
            } catch (Exception e) {
                log.error("Unexpected error occurred while sending email to {}", to, e);
            }
        });
    }

    private MimeMessage createMimeMessage(String to, String subject, String content) throws MessagingException {
        log.info("Creating MimeMessage for email to {}", to);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(new InternetAddress(username));
        helper.setTo(new InternetAddress(to));
        helper.setSubject(subject);
        helper.setText(content, true);
        return message;
    }

    @PreDestroy
    public void shutdownExecutor() {
        executorService.shutdown();
        log.info("Executor service shut down");
    }
}
