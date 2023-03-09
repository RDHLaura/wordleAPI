package com.example.wordle.service;

import com.example.wordle.dto.CreateEquipoDTO;
import com.example.wordle.modelo.Equipo;
import com.example.wordle.repo.EquipoRepo;
import com.example.wordle.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipoService extends BaseService<Equipo, Long, EquipoRepo> {

    private final EquipoRepo equipoRepo;

    public Equipo nuevoEquipo(CreateEquipoDTO nuevoEquipo) {
        Equipo equipo = new Equipo();

        equipo.setNombre( nuevoEquipo.getNombre());
        equipo.setLogo(nuevoEquipo.getLogo());
        return save(equipo);
    }

    public Equipo actualizarEquipo(Equipo nuevoEquipo, Long id) {

        Equipo equipo = equipoRepo.findById(id).orElse(null);
        if(equipo == null)
            return null;

        if (nuevoEquipo.getNombre() != null)
            equipo.setNombre( nuevoEquipo.getNombre());
        if (nuevoEquipo.getPuntos() != 0)
            equipo.setPuntos(nuevoEquipo.getPuntos());
        if (nuevoEquipo.getLogo() != null)
            equipo.setLogo(nuevoEquipo.getLogo());

        return save(equipo);
    }
}
