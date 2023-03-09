package com.example.wordle.service;

import com.example.wordle.dto.CreateJugadorDTO;
import com.example.wordle.dto.UpdateJugadorDTO;
import com.example.wordle.modelo.Equipo;
import com.example.wordle.modelo.Juego;
import com.example.wordle.modelo.Jugador;
import com.example.wordle.repo.EquipoRepo;
import com.example.wordle.repo.JugadorRepo;
import com.example.wordle.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JugadorService extends BaseService <Jugador, Long, JugadorRepo> {
    private final JugadorRepo jugadorRepo;
    private final EquipoRepo equipoRepo;

    /**
     * Crea un nuevo jugador
     * @param nuevoJugador datos del nuevo jugador
     * @return null si ya existe un jugador con ese nombre o el nuevo jugadro creado
     */
    public Jugador nuevoJugador (CreateJugadorDTO nuevoJugador) {
        //compruebo si el nombre de usuario no está libre
        List <Jugador> existeJugador = jugadorRepo.findByNombreEqualsIgnoreCase(nuevoJugador.getNombre());
        if(!existeJugador.isEmpty())
            return  null;

        Jugador jugador = new Jugador();
        jugador.setNombre(nuevoJugador.getNombre());
        jugador.setAdmin(nuevoJugador.getAdmin()? 1 : 0);
        jugador.setClave(nuevoJugador.getClave());
        jugador.setAvatar(nuevoJugador.getAvatar());

        return save(jugador);
    }

    /**
     * Actualiza los datos del jugador
     * @param nuevoJugador nuevos datos del jugador
     * @param id id del jugador a modificar
     * @return null si no existe un jugador con ese id ó el jugador actualizado
     */
    public Jugador actualizarJugador(UpdateJugadorDTO nuevoJugador, Long id){

        Equipo  equipo  = equipoRepo.findById(nuevoJugador.getIdEquipo()).orElse(null);
        Jugador jugador = jugadorRepo.findById(id).orElse(null);

        if (jugador == null)
            return null;

        if (nuevoJugador.getNombre() != null)
            jugador.setNombre( nuevoJugador.getNombre());
        if (nuevoJugador.getAdmin() != null)
            jugador.setAdmin(nuevoJugador.getAdmin()? 1 : 0);
        if (nuevoJugador.getClave() != null)
            jugador.setClave(nuevoJugador.getClave());
        if (nuevoJugador.getAvatar() != null)
            jugador.setAvatar(nuevoJugador.getAvatar());
        if (nuevoJugador.getPuntos() != 0)
            jugador.setPuntos(nuevoJugador.getPuntos());
        if (equipo != null)
            jugador.setEquipo(equipo);

        return save(jugador);
    }
}
