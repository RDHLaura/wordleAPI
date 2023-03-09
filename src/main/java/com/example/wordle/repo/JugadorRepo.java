package com.example.wordle.repo;

import com.example.wordle.modelo.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepo extends JpaRepository<Jugador, Long> {
    List<Jugador> findByNombreEqualsIgnoreCase(String nombre);

}
