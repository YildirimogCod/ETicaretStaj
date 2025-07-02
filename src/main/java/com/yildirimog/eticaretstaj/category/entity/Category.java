package com.yildirimog.eticaretstaj.category.entity;

import com.yildirimog.eticaretstaj.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> product = new ArrayList<>();
}
