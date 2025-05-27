package com.thesis.serverfurnitureecommerce.helper;

public class BaseCheck {
    public static boolean isNullOrEmpty(String text) {
        if (text == null || text.isEmpty() || text.isBlank()) {
            return true;
        }
        return false;
    }
}
