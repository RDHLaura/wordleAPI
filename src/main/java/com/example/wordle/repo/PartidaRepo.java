package com.example.wordle.repo;

import com.example.wordle.modelo.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepo extends JpaRepository<Partida, Long> {

    List<Partida> findAllByJugador_IdJugadorOrderByPuntosDesc(Long id);
    List<Partida> findAllByJuego_IdJuego(Long id);
    List<Partida> findAllByJuego_IdJuegoAndJugador_IdJugador(Long idJuego, Long idJugador);
}
