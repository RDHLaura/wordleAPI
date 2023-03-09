package com.example.wordle.controller;

import com.example.wordle.dto.CreateJuegoDTO;
import com.example.wordle.dto.UpdateJuegoDTO;
import com.example.wordle.error.JuegoNotFoundException;
import com.example.wordle.modelo.Juego;
import com.example.wordle.response.CustomResponse;
import com.example.wordle.service.JuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JuegoController {
    private final JuegoService juegoService;

    /**
     * Obtenemos todos los juegos
     * @return 404 si no hay juegos, 200 y lista de juegos si hay uno o más
     */
    @GetMapping("/juego")
    public ResponseEntity<?> getAllJuego() {
        List<Juego> result = juegoService.findAll();

        if (result.isEmpty())
            throw new JuegoNotFoundException();

        return ResponseEntity.ok(result);
    }

    /**
     * Obtengo un juego por su id
     * @param id
     * @return 404 si no encuentra el juego, si la encuentra devuelve 200 y el juego
     */
    @GetMapping("/juego/{id}")
    public ResponseEntity<?> getOneJuego(@PathVariable Long id) {

        Juego result = juegoService.findById(id).orElse(null);

        if (result == null)
            throw new JuegoNotFoundException(id);

        return ResponseEntity.ok(result);
    }

    /**
     * Crea nuevo juego
     * @param nuevo datos del nuevo juego a crear
     * @return 201 y el juego creado ó 400 si ya existe un juego con el mismo nombre
     */
    @PostMapping("/juego")
    public ResponseEntity<?> nuevoJuego(@RequestBody CreateJuegoDTO nuevo) {
         Juego saved = juegoService.nuevoJuego(nuevo);

        if(saved == null){
            CustomResponse<Juego> error = new CustomResponse<>(HttpStatus.BAD_REQUEST, "Ya existe un juego con ese nombre.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        CustomResponse<Juego> response = new CustomResponse<>(HttpStatus.CREATED, "El juego se ha creado correctamente", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualiza los datos del juego
     * @param editar datos nuevos
     * @param id del juego a modificar
     * @return 200 Ok si la edición tiene éxito, 404 si no encuentra el recurso
     */
    @PutMapping("/juego/{id}")
    public ResponseEntity<?> editarJuego(@RequestBody UpdateJuegoDTO editar, @PathVariable Long id) {

        Juego juegoActualizado = juegoService.actualizarJuego(editar, id);

        if(juegoActualizado == null){
            CustomResponse<Juego> error = new CustomResponse<>(HttpStatus.BAD_REQUEST, "No existe un juego con el id "+ id, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        CustomResponse<Juego> response = new CustomResponse<>(HttpStatus.OK, "El juego se ha modificado correctamente", juegoActualizado);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    /**
     * Borra un juego
     * @param id del juego a borrar
     * @return Código 204 sin contenido o 404 si el juego no existe
     */
    @DeleteMapping("/juego/{id}")
    public ResponseEntity<?> borrarJuego(@PathVariable Long id) {
        Juego juego = juegoService.findById(id).orElse(null);

        if (juego == null)
            throw new JuegoNotFoundException(id);

        juegoService.delete(juego);
        return ResponseEntity.noContent().build();
    }
}
