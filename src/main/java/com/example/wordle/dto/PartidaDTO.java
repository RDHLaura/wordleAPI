package com.example.wordle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class PartidaDTO {
    private Long idPartida;
    private String juegoNombre;
    private String jugadorNombre;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime datetime;
    private int intentos;
    private String palabra;
    private int puntos;
}
