package com.thesis.serverfurnitureecommerce.application.services.logs;

import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserLogRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserLogEntity;
import com.thesis.serverfurnitureecommerce.common.utils.UserUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserLogServiceImpl implements UserLogService{
    UserLogRepository userLogRepository;
    UserRepository userRepository;

    public void log(String action, String level, String message, String username, String ipAddress) {
        UserLogEntity logEntry = UserLogEntity.create();
        String nameOfUser = UserUtil.getUsername();
        if (nameOfUser.equals("anonymousUser")) {
            nameOfUser = username;
        }
        UserEntity user = userRepository.findByUsername(nameOfUser).orElse(null);
        logEntry.setUser(user);
        logEntry.setAction(action);
        logEntry.setMessage(message);
        logEntry.setLevel(level);
        logEntry.setIpAddress(ipAddress);
        userLogRepository.save(logEntry);
    }
}
