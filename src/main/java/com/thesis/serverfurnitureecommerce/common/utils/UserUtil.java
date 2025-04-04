package com.thesis.serverfurnitureecommerce.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    private UserUtil() {
        throw new AssertionError("Utility class");
    }

    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
