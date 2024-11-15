package com.thesis.serverfurnitureecommerce.model.entity;

import com.thesis.serverfurnitureecommerce.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = DatabaseConstant.REFRESH_TOKEN_TABLE)
public class RefreshTokenEntity {
    @Id
    @Column(name = "token_id")
    String tokenId;
    LocalDateTime expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    public static RefreshTokenEntity createRefreshToken() {
        return new RefreshTokenEntity();
    }
}
