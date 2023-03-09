package com.example.wordle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JugadorDTO {
    private Long    idJugador;
    private String  nombre;
    private String  avatar;
    private int     puntos;
    private String equipoNombre;

}
