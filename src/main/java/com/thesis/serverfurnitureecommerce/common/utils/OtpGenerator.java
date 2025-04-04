package com.thesis.serverfurnitureecommerce.common.utils;

import java.security.SecureRandom;
/**
 * Utility class for generating One Time Passwords (OTPs).
 * This class provides methods to generate OTPs of specified lengths.
 */
public class OtpGenerator {

    private static final SecureRandom random = new SecureRandom();

    private OtpGenerator() {
        throw new AssertionError();
    }

    /**
     * Generate a 6-digit One Time Password (OTP).
     *
     * @return a 6-digit OTP
     */
    public static int generate6DigitOtp() {
        return 100000 + random.nextInt(900000);
    }

}
