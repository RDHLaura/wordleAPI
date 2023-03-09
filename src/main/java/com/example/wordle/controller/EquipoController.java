package com.example.wordle.controller;

import com.example.wordle.dto.CreateEquipoDTO;
import com.example.wordle.error.EquipoNotFoundException;
import com.example.wordle.modelo.Equipo;
import com.example.wordle.modelo.Juego;
import com.example.wordle.response.CustomResponse;
import com.example.wordle.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    /**
     * Obtenemos todos los equipos
     * @return 404 si no hay equipos, 200 y lista de equipos si hay uno o más
     */
    @GetMapping("/equipo")
    public ResponseEntity<?> getAllEquipo(){
        List <Equipo> result = equipoService.findAll();

        if(result.isEmpty())
            throw new EquipoNotFoundException();

        return ResponseEntity.ok(result);
    }

    /**
     * Obtengo un equipo por su id
     * @param id
     * @return 404 si no encuentra el equipo, si la encuentra devuelve 200 y el equipo
     */
    @GetMapping("/equipo/{id}")
    public ResponseEntity<?> getOneEquipo(@PathVariable Long id) {
        Equipo result = equipoService.findById(id).orElse(null);
        if(result == null)
            throw new EquipoNotFoundException(id);

        return ResponseEntity.ok(result);
    }

    /**
     * Crea nuevo equipo
     * @param nuevo datos del nuevo equipo a crear
     * @return 201 y el equipo creado
     */
    @PostMapping("/equipo")
    public ResponseEntity<?> nuevoEquipo(@RequestBody CreateEquipoDTO nuevo) {
        Equipo saved = equipoService.nuevoEquipo(nuevo);

        if(saved == null){
            CustomResponse<Equipo> error = new CustomResponse<>(HttpStatus.BAD_REQUEST, "Ya existe un equipo con ese nombre.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        CustomResponse<Equipo> response = new CustomResponse<>(HttpStatus.CREATED, "El equipo se ha creado correctamente", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualiza los datos del equipo
     * @param editar datos nuevos
     * @param id del equipo a modificar
     * @return 200 Ok si la edición tiene éxito, 404 si no encuentra el recurso
     */
    @PutMapping("/equipo/{id}")
    public ResponseEntity<?> editarEquipo(@RequestBody Equipo editar, @PathVariable Long id) {

        Equipo equipoActualizado = equipoService.actualizarEquipo(editar, id);

        if(equipoActualizado == null){
            CustomResponse<Equipo> error = new CustomResponse<>(HttpStatus.BAD_REQUEST, "No existe un equipo con el id "+ id, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        CustomResponse<Equipo> response = new CustomResponse<>(HttpStatus.OK, "El equipo se ha modificado correctamente", equipoActualizado);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Borra un equipo
     * @param id del equipo a borrar
     * @return Código 204 si se ha borrado correctamente o 404 si el equipo no existe
     */
    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<?> borrarEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id).orElse(null);
        if (equipo == null)
            throw new EquipoNotFoundException(id);

        equipoService.delete(equipo);
        return ResponseEntity.noContent().build();
    }


}
