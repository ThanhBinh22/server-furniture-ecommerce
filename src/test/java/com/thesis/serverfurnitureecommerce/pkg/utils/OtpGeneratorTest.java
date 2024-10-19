package com.thesis.serverfurnitureecommerce.pkg.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OtpGeneratorTest {

    @Test
    @DisplayName("Kiểm tra rằng OTP được tạo ra có 6 chữ số")
    public void testGenerate6DigitOtp_ValidRange() {
        int otp = OtpGenerator.generate6DigitOtp();
        assertTrue(otp >= 100000 && otp <= 999999, "OTP nên có 6 chữ số");
    }

    @Test
    @DisplayName("Kiểm tra rằng hai OTP được tạo ra khác nhau")
    public void testGenerateUniqueOtps_UniqueValues() {
        int otp1 = OtpGenerator.generate6DigitOtp();
        int otp2 = OtpGenerator.generate6DigitOtp();
        assertNotEquals(otp1, otp2, "Hai OTP được tạo ra nên khác nhau");
    }

    @Test
    @DisplayName("Kiểm tra tính duy nhất cho tất cả các OTP")
    public void testGenerate6DigitOtps_MultipleCalls() {
        int[] otps = new int[1000];
        for (int i = 0; i < 1000; i++) {
            otps[i] = OtpGenerator.generate6DigitOtp();
        }
        for (int i = 0; i < otps.length; i++) {
            for (int j = i + 1; j < otps.length; j++) {
                assertNotEquals(otps[i], otps[j], "Mỗi OTP nên là duy nhất");
            }
        }
    }

    @Test
    @DisplayName("Kiểm tra rằng giá trị tối thiểu là 100000")
    public void testGenerate6DigitOtp_LowerBound() {
        int otp = OtpGenerator.generate6DigitOtp();
        assertTrue(otp >= 100000, "OTP nên lớn hơn hoặc bằng 100000");
    }

    @Test
    @DisplayName("Kiểm tra rằng giá trị tối đa là 999999")
    public void testGenerate6DigitOtp_UpperBound() {
        int otp = OtpGenerator.generate6DigitOtp();
        assertTrue(otp <= 999999, "OTP nên nhỏ hơn hoặc bằng 999999");
    }

    @Test
    @DisplayName("Kiểm tra rằng việc tạo OTP trong vòng 1 giây")
    public void testGenerate6DigitOtp_Performance() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            OtpGenerator.generate6DigitOtp();
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        assertTrue(duration < 1_000_000_000, "Thời gian tạo 100000 OTP nên nhỏ hơn 1 giây");
    }
}
