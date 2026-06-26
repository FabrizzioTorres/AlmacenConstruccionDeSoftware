package com.example.ALMACENMULTIPRO.repository.jpa;

import com.example.ALMACENMULTIPRO.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository
        extends JpaRepository<ProductoEntity, Long> {
}