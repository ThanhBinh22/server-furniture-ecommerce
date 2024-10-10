package com.thesis.serverfurnitureecommerce.constant;

public class StringConstant {
    public static final String USER_AND_PASSWORD_NOT_MATCH = "Username and password do not match";
    public static final String ACCOUNT_IS_LOCKED = "User is locked";
    public static final String OTP_NUll = "OTP cannot be empty or blank";
    public static final String NOT_AUTHENTICATED = "You are not authorized to access this resource";
    public static final String JWT_SIGNATURE_INVALID = "The JWT signature is invalid";
    public static final String JWT_TOKEN_EXPIRED = "The JWT token has expired";
    public static final String USER_NOT_FOUND = "User not found";

    public static final String TEMPLATE_EMAIL_VERIFY = """
        <div style="font-family: Arial, sans-serif; color: #333;">
            <p style="font-size: 16px;">Chào bạn,</p>
            <p style="font-size: 16px;">
                Cảm ơn bạn đã đăng ký tài khoản với chúng tôi! Để xác thực tài khoản của bạn, 
                vui lòng nhập mã OTP dưới đây:
            </p>                        
            <p style="font-size: 18px; font-weight: bold; color: #D19C97; text-align: center;">
                Mã OTP: %s
            </p>
            <p style="font-size: 16px;">
                Vui lòng nhập mã này vào ứng dụng để hoàn tất quá trình xác thực tài khoản.
            </p>
            <p style="font-size: 16px;">
                Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.
            </p>
            <p style="font-size: 16px;">Trân trọng,</p>
            <p style="font-size: 16px; font-style: italic;">Đội ngũ hỗ trợ</p>
        </div>
        """;

}
