package com.example.ALMACENMULTIPRO.repository.jpa;

import com.example.ALMACENMULTIPRO.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorJpaRepository
        extends JpaRepository<
                ProveedorEntity,
                String
                > {
}
