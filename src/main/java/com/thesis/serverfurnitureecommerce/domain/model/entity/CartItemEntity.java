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
@Table(name = DatabaseConstant.CART_ITEMS_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemEntity {
    @Id
    @Column(name = "id")
    String id;
    Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    CartEntity cart;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    ProductEntity product;

    public static CartItemEntity create() {
        return new CartItemEntity();
    }
}
