package com.thesis.serverfurnitureecommerce.domain.repository;

import com.thesis.serverfurnitureecommerce.domain.model.entity.UserLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLogEntity, Long> {
}
