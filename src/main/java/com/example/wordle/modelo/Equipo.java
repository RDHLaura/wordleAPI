package com.example.wordle.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idEquipo")
    private Long    idEquipo;
    private String  nombre;
    private int     puntos = 0;
    private String  logo;
}
