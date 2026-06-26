package com.example.ALMACENMULTIPRO.repository.jpa;

import com.example.ALMACENMULTIPRO.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaJpaRepository
        extends JpaRepository<CategoriaEntity, Long> {

}