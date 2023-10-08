package com.techchallenge.appgestaodeeventos.repository;

import com.techchallenge.appgestaodeeventos.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long> {
}
