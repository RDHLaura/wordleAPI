package com.example.wordle.controller;

import com.example.wordle.modelo.Juego;
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

        return (result.isEmpty()) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(result);
    }

    /**
     * Obtengo un juego por su id
     * @param id
     * @return 404 si no encuentra el juego, si la encuentra devuelve 200 y el juego
     */
    @GetMapping("/juego/{id}")
    public ResponseEntity<?> getOneJuego(@PathVariable Long id) {

        Juego result = juegoService.findById(id).orElse(null);

        return (result == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(result);
    }

    /**
     * Crea nuevo juego
     * @param nuevo datos del nuevo juego a crear
     * @return 201 y el juego creado
     */
    @PostMapping("/juego")
    public ResponseEntity<?> nuevoJuego(@RequestBody Juego nuevo) {

        Juego saved = juegoService.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Actualiza los datos del juego
     * @param editar datos nuevos
     * @param id del juego a modificar
     * @return 200 Ok si la edición tiene éxito, 404 si no encuentra el recurso
     */
    @PutMapping("/juego/{id}")
    public ResponseEntity<?> editarJuego(@RequestBody Juego editar, @PathVariable Long id) {

        Juego juegoActualizado = juegoService.actualizarJuego(editar, id);

        return (juegoActualizado == null) ?
            ResponseEntity.notFound().build() :
            ResponseEntity.ok(juegoActualizado);
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
            return ResponseEntity.notFound().build();

        juegoService.delete(juego);
        return ResponseEntity.noContent().build();
    }
}
