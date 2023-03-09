package com.example.wordle.repo;

import com.example.wordle.modelo.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepo extends JpaRepository<Jugador, Long> {
}
