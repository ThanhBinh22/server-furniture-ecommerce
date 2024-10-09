package com.thesis.serverfurnitureecommerce.internal.services.account;

import com.thesis.serverfurnitureecommerce.internal.repositories.IUserRepository;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCleanupService {

    IUserRepository userRepository;

    @Scheduled(fixedRate = 500000)
    public void removeExpiredAccounts() {
        log.info("removeExpiredAccounts");
        List<UserEntity> expiredAccounts = userRepository.findByIsActiveAndOtpExpiredBefore((short) 0);

        if (!expiredAccounts.isEmpty()) {
            userRepository.deleteAll(expiredAccounts);
            log.info("Đã xóa {} tài khoản hết hạn.", expiredAccounts.size());
        }
    }
}
