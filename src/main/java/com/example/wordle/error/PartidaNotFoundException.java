package com.example.wordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidaNotFoundException extends RuntimeException{
    public PartidaNotFoundException(){
        super("No se han encontrado datos de ninguna partida");
    }
    public PartidaNotFoundException(Long id){
        super("No se ha encontrado la partida con el id " + id);
    }

}
