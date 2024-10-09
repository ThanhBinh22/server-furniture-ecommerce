package com.thesis.serverfurnitureecommerce.pkg.utils;

public class Random {

    private Random() {
        throw new AssertionError();
    }

    public static int generate6DigitOtp() {
        return 100000 + (int) (Math.random() * 900000);
    }

}
