package com.yildirimog.eticaretstaj.category.repository;

import com.yildirimog.eticaretstaj.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
