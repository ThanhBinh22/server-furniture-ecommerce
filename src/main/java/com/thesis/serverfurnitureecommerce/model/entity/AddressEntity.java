package com.thesis.serverfurnitureecommerce.model.entity;

import com.thesis.serverfurnitureecommerce.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = DatabaseConstant.ADDRESS_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "address_line")
    String addressLine;
    String ward;
    String district;
    String province;
    String country;
    Boolean isDefault;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;
    @OneToMany(mappedBy = "address")
    Set<OrderEntity> orderEntities;

    public static AddressEntity create() {
        return new AddressEntity();
    }
}
