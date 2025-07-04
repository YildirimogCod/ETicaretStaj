package com.yildirimog.eticaretstaj.product.entity;

import com.yildirimog.eticaretstaj.cart.entity.CartItem;
import com.yildirimog.eticaretstaj.category.entity.Category;
import com.yildirimog.eticaretstaj.order.entity.OrderItem;
import com.yildirimog.eticaretstaj.wishlist.entity.Wishlist;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int stockQuantity;
    private BigDecimal price;
    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany(mappedBy = "products")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany
    private List<OrderItem> orderItem;

}
