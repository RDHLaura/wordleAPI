package com.example.wordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipoNotFoundException extends RuntimeException {
    public EquipoNotFoundException(){
        super("No se han encontrado datos de ningún equipo.");
    }
    public EquipoNotFoundException(Long id){
        super("No se ha encontrado el equipo con el id " + id);
    }

}
