package com.thesis.serverfurnitureecommerce.application.services.logs;

public interface UserLogService {
    void log(String action, String level, String message, String username, String ipAddress);
}
