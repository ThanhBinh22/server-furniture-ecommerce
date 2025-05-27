package com.thesis.serverfurnitureecommerce.common.utils;

import java.security.SecureRandom;

public final class OtpGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String DEFAULT_CHARSET = "0123456789";
    private static final String ALPHANUMERIC_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private OtpGenerator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * @deprecated This method is deprecated. Use {@link #generateNumericOtp(int)} instead.
     */
    @Deprecated
    public static int generate6DigitOtp() {
        return 100000 + RANDOM.nextInt(900000);
    }

    // 3812
    public static String generateNumericOtp(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("OTP length must be positive");
        }
        return generateFromCharset(length, DEFAULT_CHARSET);
    }

    // A3f9Z1qX
    public static String generateAlphanumericOtp(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("OTP length must be positive");
        }
        return generateFromCharset(length, ALPHANUMERIC_CHARSET);
    }

    private static String generateFromCharset(int length, String charset) {
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(charset.charAt(RANDOM.nextInt(charset.length())));
        }
        return otp.toString();
    }
}
