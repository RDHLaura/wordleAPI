package com.example.wordle.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idPartida")
    private Long idPartida;

    @ManyToOne
    @JoinColumn(name = "Jugador_idJugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "Juego_idJuego")
    private Juego juego;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime datetime = LocalDateTime.now();

    private int intentos;
    private String palabra;
    private int puntos;
}