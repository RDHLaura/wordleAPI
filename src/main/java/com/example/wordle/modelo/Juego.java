package com.example.wordle.modelo;

import com.example.wordle.response.CustomResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idJuego")
    private Long idJuego;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;
    private String instrucciones;
    private int intentosMax;
}
