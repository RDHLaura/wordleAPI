package com.example.wordle.dto;

import com.example.wordle.modelo.Dificultad;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateJuegoDTO {

    private String nombre;
    private Dificultad dificultad;
    private String instrucciones;
    private int intentosMax;
}
