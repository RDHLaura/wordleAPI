package com.example.wordle.dto.converter;

import com.example.wordle.dto.CreateEquipoDTO;
import com.example.wordle.dto.JugadorDTO;
import com.example.wordle.dto.PartidaDTO;
import com.example.wordle.modelo.Equipo;
import com.example.wordle.modelo.Juego;
import com.example.wordle.modelo.Jugador;
import com.example.wordle.modelo.Partida;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOConverter {
    private final ModelMapper modelMapper;
    public JugadorDTO convertJugadorToDto(Jugador jugador) {
        return modelMapper.map(jugador, JugadorDTO.class);
    }

    public PartidaDTO convertPartidaToDto(Partida partida) {
        return modelMapper.map(partida, PartidaDTO.class);
    }


}
