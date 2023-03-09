package com.example.wordle.controller;

import com.example.wordle.dto.CreateJugadorDTO;
import com.example.wordle.dto.JugadorDTO;
import com.example.wordle.dto.UpdateJugadorDTO;
import com.example.wordle.dto.converter.DTOConverter;
import com.example.wordle.modelo.Equipo;
import com.example.wordle.modelo.Jugador;
import com.example.wordle.repo.EquipoRepo;
import com.example.wordle.repo.JugadorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorRepo jugadorRepo;
    private final DTOConverter dtoConverter;
    private final EquipoRepo equipoRepo;

    /**
     * Obtenemos todos los jugadores
     * @return 404 si no hay jugadores, 200 y lista de jugadores si hay uno o más
     */
    @GetMapping("/jugador")
    public ResponseEntity<?> getAllJugador() {
        List <Jugador> result = jugadorRepo.findAll();
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        List<JugadorDTO> dtoList = result.stream()
                                         .map(dtoConverter::convertJugadorToDto)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/jugador/{id}")
    public ResponseEntity<?> getOneJugador (@PathVariable Long id){
        Jugador result = jugadorRepo.findById(id).orElse(null);

        return (result == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(result);
    }

    @PostMapping("/jugador")
    public ResponseEntity<?> nuevoJugador(@RequestBody CreateJugadorDTO nuevo) {

        //Equipo equipo        = equipoRepo.findById(nuevo.getIdEquipo()).orElse(null);
        Jugador nuevojugador = new Jugador();

        nuevojugador.setNombre(nuevo.getNombre());
        nuevojugador.setAdmin(nuevo.getAdmin());
        nuevojugador.setClave(nuevo.getClave());
        nuevojugador.setAvatar(nuevo.getAvatar());
        //nuevojugador.setEquipo(equipo);
        nuevojugador.setPuntos(0); //los puntos se inicializan en 0

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(jugadorRepo.save(nuevojugador));
    }


    @PutMapping("/jugador/{id}")
    public ResponseEntity<?> editarJugador(@RequestBody UpdateJugadorDTO editar, @PathVariable Long id) {

        Equipo  equipo   = equipoRepo.findById(editar.getIdEquipo()).orElse(null);
        Jugador jugador = jugadorRepo.findById(id).orElse(null);

        if (jugador == null)
            return ResponseEntity.notFound().build();


        if (editar.getNombre() != null)
            jugador.setNombre( editar.getNombre());
        if (editar.getAdmin() != 0)
            jugador.setAdmin(editar.getAdmin());
        if (editar.getClave() != null)
            jugador.setClave(editar.getClave());
        if (editar.getAvatar() != null)
            jugador.setAvatar(editar.getAvatar());
        if (editar.getPuntos() != 0)
            jugador.setPuntos(editar.getPuntos());
        if (equipo != null)
            jugador.setEquipo(equipo);

        return ResponseEntity.ok(jugadorRepo.save(jugador));
    }

    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<?> borrarJugador(@PathVariable Long id) {
        Jugador jugador = jugadorRepo.findById(id).orElse(null);
        if (jugador == null)
            return ResponseEntity.notFound().build();

        jugadorRepo.delete(jugador);
        return ResponseEntity.noContent().build();
    }
}

