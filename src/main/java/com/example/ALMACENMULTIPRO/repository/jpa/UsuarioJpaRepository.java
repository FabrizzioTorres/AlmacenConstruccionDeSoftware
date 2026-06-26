package com.example.ALMACENMULTIPRO.repository.jpa;

import com.example.ALMACENMULTIPRO.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository
        extends JpaRepository<UsuarioEntity,String> {

}