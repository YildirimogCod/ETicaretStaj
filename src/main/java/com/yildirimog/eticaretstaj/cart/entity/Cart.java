package com.yildirimog.eticaretstaj.cart.entity;

import com.yildirimog.eticaretstaj.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
