package com.example.wordle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateJugadorDTO {
    private int     admin;
    private String  nombre;
    private String  clave;
    private String  avatar;
    //private Long    idEquipo;
}
