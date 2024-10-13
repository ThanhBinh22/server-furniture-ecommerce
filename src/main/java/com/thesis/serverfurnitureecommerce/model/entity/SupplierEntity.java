package com.thesis.serverfurnitureecommerce.model.entity;

import com.thesis.serverfurnitureecommerce.constant.DatabaseConstant;
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
@Table(name = DatabaseConstant.SUPPLIER_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, unique = true)
    String name;
    @Column(name = "contact_email")
    String contactEmail;
    @Column(name = "contact_phone")
    String contactPhone;
    String address;
    String country;
    String website;
    @Column(name = "is_active", nullable = false)
    Short isActive;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<ProductEntity> products = new HashSet<>();
}
