package com.example.wordle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJugadorDTO {

    private String  nombre;
    private Boolean admin;
    private String  clave;
    private String  avatar;
    private int     puntos;
    private Long    idEquipo;
}
