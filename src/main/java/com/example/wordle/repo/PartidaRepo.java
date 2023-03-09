package com.example.wordle.repo;

import com.example.wordle.modelo.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepo extends JpaRepository<Partida, Long> {
}
