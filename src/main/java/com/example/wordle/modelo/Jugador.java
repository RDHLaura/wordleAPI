package com.example.wordle.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Jugador {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name = "idJugador")
    private Long    idJugador;
    private String  nombre;
    private int     admin;
    private String  clave;
    private String  avatar;
    private int     puntos;
    @ManyToOne
    @JoinColumn(name = "Equipo_idEquipo")
    private Equipo  equipo;
}
