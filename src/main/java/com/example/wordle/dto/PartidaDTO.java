package com.example.wordle.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class PartidaDTO {
    private Long idPartida;
    private String juegoNombre;
    private String jugadorNombre;
    private Date datetime;
    private int intentos;
    private String palabra;
    private int puntos;
}
