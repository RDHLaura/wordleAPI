package com.example.wordle.repo;

import com.example.wordle.modelo.Equipo;
import com.example.wordle.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepo extends JpaRepository<Equipo, Long> {
    List<Equipo> findByNombreEqualsIgnoreCase(String nombre);
}
