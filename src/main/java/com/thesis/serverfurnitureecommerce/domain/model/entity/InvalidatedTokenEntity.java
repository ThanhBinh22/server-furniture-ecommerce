package com.thesis.serverfurnitureecommerce.domain.model.entity;

import com.thesis.serverfurnitureecommerce.common.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseConstant.INVALIDATED_TOKENS_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidatedTokenEntity {
    @Id
    @Column(name = "token_id")
    String tokenId;
    LocalDateTime expired;

    public static InvalidatedTokenEntity createInvalidatedToken() {
        return new InvalidatedTokenEntity();
    }
}
