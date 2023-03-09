package com.example.wordle.repo;

import com.example.wordle.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepo extends JpaRepository<Juego, Long> {
}
