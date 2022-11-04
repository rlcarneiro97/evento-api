package com.eventoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eventoapi.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, String> {
    
}
