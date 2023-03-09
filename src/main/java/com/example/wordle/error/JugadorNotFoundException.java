package com.example.wordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JugadorNotFoundException extends RuntimeException {
    public JugadorNotFoundException(){
        super("No se han encontrado datos de ningún jugador");
    }
    public JugadorNotFoundException(Long id){
        super("No se ha encontrado el jugador con el id " + id);
    }

}
