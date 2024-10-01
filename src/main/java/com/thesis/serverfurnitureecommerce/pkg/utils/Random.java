package com.thesis.serverfurnitureecommerce.pkg.utils;

public class Random {
    public static int generate6DigitOtp() {
        return 100000 + (int) (Math.random() * 900000);
    }
}
