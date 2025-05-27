package com.thesis.serverfurnitureecommerce.common.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {

    private static final Locale VIETNAM_LOCALE = new Locale("vi", "VN");
    private static final NumberFormat VND_FORMATTER = NumberFormat.getInstance(VIETNAM_LOCALE);

    private CurrencyUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String formatCurrencyVND(int amount) {
        synchronized (VND_FORMATTER) {
            return VND_FORMATTER.format(amount) + " VNĐ";
        }
    }
}

