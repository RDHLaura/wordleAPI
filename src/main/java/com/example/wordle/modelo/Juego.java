package com.example.wordle.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idJuego")
    private Long idJuego;
    private String nombre;
    private String dificultad;
    private String instrucciones;
    private int intentosMax;
}
