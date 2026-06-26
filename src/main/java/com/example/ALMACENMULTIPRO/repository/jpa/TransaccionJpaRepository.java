package com.example.ALMACENMULTIPRO.repository.jpa;

import com.example.ALMACENMULTIPRO.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionJpaRepository
        extends JpaRepository<
        TransaccionEntity,
        Long> {

}