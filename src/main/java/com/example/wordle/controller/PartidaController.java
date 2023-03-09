package com.example.wordle.controller;

import com.example.wordle.dto.CreatePartidaDTO;
import com.example.wordle.dto.PartidaDTO;
import com.example.wordle.dto.converter.DTOConverter;
import com.example.wordle.error.JugadorNotFoundException;
import com.example.wordle.error.PartidaNotFoundException;
import com.example.wordle.modelo.Jugador;
import com.example.wordle.modelo.Partida;
import com.example.wordle.response.CustomResponse;
import com.example.wordle.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PartidaController {
    private final PartidaService partidaService;
    private final DTOConverter dtoConverter;

    /**
     * Obtenemos todas las partidas
     * @return 404 si no hay partidas, 200 y lista de partidas si hay una o más
     */
    @GetMapping("/partida")
    public ResponseEntity<?> getAllPartida() {
        List <Partida> result = partidaService.findAll();

        if(result.isEmpty())
            throw new PartidaNotFoundException();

        List<PartidaDTO> dtoList = result.stream()
                .map(dtoConverter::convertPartidaToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    /**
     * Obtengo una partida por su id
     * @param id
     * @return 404 si no encuentra la partida, si la encuentra devuelve 200 y la partida
     */
    @GetMapping("/partida/{id}")
    public ResponseEntity<?> getOnePartida (@PathVariable Long id){
        Partida result = partidaService.findById(id).orElse(null);
        if(result == null)
            throw new PartidaNotFoundException(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Crea nueva partida
     * @param nueva datos de la partida a crear
     * @return 201 y la partida creada ó 400 si ya existe un jugador con el mismo nombre
     */
    @PostMapping("/partida")
    public ResponseEntity<?> nuevaPartida(@RequestBody CreatePartidaDTO nueva){
        Partida saved = partidaService.nuevaPartida(nueva);

        CustomResponse<Partida> response = new CustomResponse<>(HttpStatus.CREATED, "La partida se ha creado correctamente", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Borra una partida
     * @param id de la partida a borrar
     * @return Código 204 sin contenido o 404 si la partida no existe
     */
  @DeleteMapping("/partida/{id}")
    public ResponseEntity<?> borrarPartida(@PathVariable Long id) {
        Partida partida = partidaService.findById(id).orElse(null);

        if(partida == null)
            throw new PartidaNotFoundException(id);

        partidaService.delete(partida);
        return ResponseEntity.noContent().build();
  }


}
