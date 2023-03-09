package com.example.wordle.service;

import com.example.wordle.dto.CreateJuegoDTO;
import com.example.wordle.dto.UpdateJuegoDTO;
import com.example.wordle.modelo.Juego;
import com.example.wordle.repo.JuegoRepo;
import com.example.wordle.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JuegoService extends BaseService<Juego, Long, JuegoRepo> {

    private final JuegoRepo juegoRepo;

    public Juego nuevoJuego(CreateJuegoDTO nuevoJuego) {
        List <Juego> existeJuego = juegoRepo.findByNombreEqualsIgnoreCase(nuevoJuego.getNombre());
        if(!existeJuego.isEmpty())
            return  null;

        Juego juego = new Juego();
        juego.setNombre(nuevoJuego.getNombre());
        juego.setInstrucciones(nuevoJuego.getInstrucciones());
        juego.setDificultad(nuevoJuego.getDificultad());
        juego.setIntentosMax(nuevoJuego.getIntentosMax());

       return save(juego);
    }

    public Juego actualizarJuego (UpdateJuegoDTO nuevoJuego, Long id) {
        Juego juego = juegoRepo.findById(id).orElse(null);
        if(juego == null)
            return null;

        if (nuevoJuego.getNombre() != null)
            juego.setNombre( nuevoJuego.getNombre());
        if (nuevoJuego.getInstrucciones() != null)
            juego.setInstrucciones(nuevoJuego.getInstrucciones());
        if (nuevoJuego.getIntentosMax() != 0)
            juego.setIntentosMax(nuevoJuego.getIntentosMax());
        if (nuevoJuego.getDificultad() != null)
            juego.setDificultad(nuevoJuego.getDificultad());

        return save(juego);
    }

}
