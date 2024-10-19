package com.thesis.serverfurnitureecommerce.pkg.utils;

import java.security.SecureRandom;

public class OtpGenerator {
    private static final SecureRandom random = new SecureRandom();

    private OtpGenerator() {
        throw new AssertionError();
    }

    public static int generate6DigitOtp() {
        return 100000 + random.nextInt(900000);
    }

}
