package com.example.wordle.repo;

import com.example.wordle.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegoRepo extends JpaRepository<Juego, Long> {
    List<Juego> findByNombreEqualsIgnoreCase(String nombre);
}
