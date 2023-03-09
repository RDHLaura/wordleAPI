package com.example.wordle.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    private int     puntos = 0; //los puntos se inicializan en 0

    //se inicia el equipo en null para que elija una vez se haya logueado en el juego
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "Equipo_idEquipo")
    private Equipo  equipo = null;
}
