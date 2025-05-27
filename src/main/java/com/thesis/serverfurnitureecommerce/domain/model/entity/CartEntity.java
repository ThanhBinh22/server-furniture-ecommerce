package com.thesis.serverfurnitureecommerce.domain.model.entity;

import com.thesis.serverfurnitureecommerce.common.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DatabaseConstant.CART_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartEntity extends BaseEntity {
    @Id
    @Column(name = "id")
    String id;
    Integer quantity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<CartItemEntity> cartItems = new HashSet<>();


}
