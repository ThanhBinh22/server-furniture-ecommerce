package com.thesis.serverfurnitureecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.serverfurnitureecommerce.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DatabaseConstant.USER_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String username;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String email;
    String phone;
    @Column(name = "full_name")
    String fullName;
    @JsonIgnore
    @Column(name = "otp")
    Integer otp;
    @JsonIgnore
    @Column(name = "otp_expired")
    LocalDateTime otpExpired;
    @Column(name = "oauth2_id")
    String oauth2Id;
    @Column(name = "oauth2_provider")
    String oauth2Provider;
    @Column(name = "is_active", nullable = false)
    Short isActive;
    @Column(name = "is_locked", nullable = false)
    Short isLocked;
    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<UserLogEntity> logs = new HashSet<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    CartEntity cart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<OrderEntity> orders = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<WishlistEntity> wishlist;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<ReviewEntity> reviews = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<AddressEntity> addresses = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLocked == null || this.isLocked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive != null && this.isActive == 1;
    }
}
