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
@Table(name = DatabaseConstant.ROLE_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, unique = true)
    String name;
    @OneToMany(mappedBy = "role")
    Set<UserEntity> users = new HashSet<>();
}
