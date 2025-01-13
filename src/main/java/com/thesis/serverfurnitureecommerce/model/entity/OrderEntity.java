package com.thesis.serverfurnitureecommerce.model.entity;

import com.thesis.serverfurnitureecommerce.constant.DatabaseConstant;
import com.thesis.serverfurnitureecommerce.pkg.enums.StatusOrderEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseConstant.ORDER_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "total_amount")
    Double totalAmount;
    @Setter
    StatusOrderEnum status;
    @Column(name = "payment")
    String payment;
    String message;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    Set<OrderItemEntity> orderItems = new HashSet<>();
    @ManyToMany(mappedBy = "orders")
    Set<PromotionEntity> promotions = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "address_id")
    AddressEntity address;

    public static OrderEntity create(){
        return new OrderEntity();
    }
}

