package com.techchallenge.appgestaodeeventos.repository;

import com.techchallenge.appgestaodeeventos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
