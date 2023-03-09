package com.example.wordle.service;

import com.example.wordle.modelo.Juego;
import com.example.wordle.repo.JuegoRepo;
import com.example.wordle.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JuegoService extends BaseService<Juego, Long, JuegoRepo> {

    private final JuegoRepo juegoRepo;

    public Juego actualizarJuego (Juego nuevoJuego, Long id) {
        Juego juego = juegoRepo.findById(id).orElse(null);
        if(juego == null)
            return null;

        if (nuevoJuego.getNombre() != null)
            juego.setNombre( nuevoJuego.getNombre());
        if (nuevoJuego.getDificultad() != null)
            juego.setDificultad(nuevoJuego.getDificultad());
        if (nuevoJuego.getInstrucciones() != null)
            juego.setInstrucciones(nuevoJuego.getInstrucciones());
        if (nuevoJuego.getIntentosMax() != 0)
            juego.setIntentosMax(nuevoJuego.getIntentosMax());

        return save(juego);
    }

}
