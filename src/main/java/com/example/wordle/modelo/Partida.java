package com.example.wordle.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Date datetime = new Date(); //TODO arreglar fecha
    private int intentos;
    private String palabra;
    private int puntos;
}