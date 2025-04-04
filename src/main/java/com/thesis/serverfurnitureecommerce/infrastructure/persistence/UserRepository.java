package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("""
            SELECT u FROM UserEntity u WHERE u.username = :name
            """)
    Optional<UserEntity> findByUsername(String name);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByOtp(Integer otp);


    @Query("""
            SELECT u FROM UserEntity u WHERE u.isActive = :isActive AND u.otpExpired < CURRENT_TIMESTAMP
            """)
    List<UserEntity> findByIsActiveAndOtpExpiredBefore(Short isActive);
}
