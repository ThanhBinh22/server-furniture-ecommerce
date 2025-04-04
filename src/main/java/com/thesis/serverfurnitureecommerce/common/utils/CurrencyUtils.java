package com.thesis.serverfurnitureecommerce.common.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {

    private CurrencyUtils() {
        throw new AssertionError();
    }

    public static String formatCurrencyVND(int amount) {
        Locale vietnamLocale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getInstance(vietnamLocale);
        return currencyFormatter.format(amount) + " VNĐ";
    }

}
