package com.example.wordle.controller;

import com.example.wordle.dto.CreateJugadorDTO;
import com.example.wordle.dto.JugadorDTO;
import com.example.wordle.dto.UpdateJugadorDTO;
import com.example.wordle.dto.converter.DTOConverter;
import com.example.wordle.error.JugadorNotFoundException;
import com.example.wordle.modelo.Jugador;
import com.example.wordle.repo.EquipoRepo;
import com.example.wordle.response.CustomResponse;
import com.example.wordle.service.JugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorService jugadorService;
    private final DTOConverter dtoConverter;


    /**
     * Obtenemos todos los jugadores
     * @return 404 si no hay jugadores, 200 y lista de jugadores si hay uno o más
     */
    @GetMapping("/jugador")
    public ResponseEntity<?> getAllJugador() {
        List <Jugador> result = jugadorService.findAll();

        if (result.isEmpty())
            throw new JugadorNotFoundException();

        List<JugadorDTO> dtoList = result.stream()
                                         .map(dtoConverter::convertJugadorToDto)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    /**
     * Obtengo un jugador por su id
     * @param id
     * @return 404 si no encuentra el jugador, si la encuentra devuelve 200 y el jugador
     */
    @GetMapping("/jugador/{id}")
    public ResponseEntity<?> getOneJugador (@PathVariable Long id){

        Jugador result = jugadorService.findById(id).orElse(null);

        if (result == null)
            throw new JugadorNotFoundException(id);

        return ResponseEntity.ok(result);
    }

    /**
     * Crea nuevo jugador
     * @param nuevo datos del nuevo juego a crear
     * @return 201 y el juego creado ó 400 si ya existe un jugador con el mismo nombre
     */
    @PostMapping("/jugador")
    public ResponseEntity<?> nuevoJugador(@RequestBody CreateJugadorDTO nuevo) {
        Jugador saved = jugadorService.nuevoJugador(nuevo);

        if(saved == null){
            CustomResponse<Jugador> error = new CustomResponse<>(HttpStatus.BAD_REQUEST, "Ya existe un jugador con ese nombre.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        CustomResponse<Jugador> response = new CustomResponse<>(HttpStatus.CREATED, "El jugador se ha creado correctamente", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualiza los datos del jugador
     * @param editar datos nuevos
     * @param id del jugador a modificar
     * @return 200 Ok si la edición tiene éxito, 404 si no encuentra el recurso
     */
    @PutMapping("/jugador/{id}")
    public ResponseEntity<?> editarJugador(@RequestBody UpdateJugadorDTO editar, @PathVariable Long id) {

        Jugador jugadorActualizado = jugadorService.actualizarJugador(editar, id);

        if(jugadorActualizado == null){
            CustomResponse<Jugador> error = new CustomResponse<>(HttpStatus.BAD_REQUEST, "No existe un jugador con el id "+ id, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        CustomResponse<Jugador> response = new CustomResponse<>(HttpStatus.OK, "El jugador se ha modificado correctamente", jugadorActualizado);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Borra un jugador
     * @param id del jugador a borrar
     * @return Código 204 sin contenido o 404 si el jugador no existe
     */
    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<?> borrarJugador(@PathVariable Long id) {
        Jugador jugador = jugadorService.findById(id).orElse(null);

        if (jugador == null)
            throw new JugadorNotFoundException(id);

        jugadorService.delete(jugador);
        return ResponseEntity.noContent().build();
    }
}

