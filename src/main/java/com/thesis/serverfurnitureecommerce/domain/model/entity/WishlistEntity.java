package com.thesis.serverfurnitureecommerce.domain.model.entity;

import com.thesis.serverfurnitureecommerce.common.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseConstant.WISH_LIST_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    ProductEntity product;

    public static WishlistEntity create() {
         return new WishlistEntity();
    }
}
