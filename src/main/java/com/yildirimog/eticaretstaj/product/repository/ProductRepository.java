package com.yildirimog.eticaretstaj.product.repository;

import com.yildirimog.eticaretstaj.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
