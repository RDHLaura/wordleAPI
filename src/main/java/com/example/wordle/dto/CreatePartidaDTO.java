package com.example.wordle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePartidaDTO {

    private Long idJugador;
    private Long idJuego;
    private int intentos;
    private String palabra;

}
