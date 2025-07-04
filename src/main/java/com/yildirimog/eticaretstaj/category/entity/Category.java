package com.yildirimog.eticaretstaj.category.entity;

import com.yildirimog.eticaretstaj.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "category", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Set<Product> product = new HashSet<>();
}
