package com.thesis.serverfurnitureecommerce.common.utils;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConverterService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/VND";

    public double getExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(API_URL, String.class);
            JSONObject jsonObject = new JSONObject(response);
            double rateToUSD = jsonObject.getJSONObject("rates").getDouble("USD");
            if (rateToUSD > 1) {
                throw new IllegalArgumentException("Tỷ giá từ API không hợp lệ: " + rateToUSD);
            }
            return rateToUSD;
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy tỷ giá từ API: " + e.getMessage());
        }
    }

    public double convertVNDToUSD(double amountInVND) {
        if (amountInVND < 0) {
            throw new IllegalArgumentException("Số tiền VND không được là số âm");
        }
        double exchangeRate = getExchangeRate();
        return amountInVND * exchangeRate;
    }
}
