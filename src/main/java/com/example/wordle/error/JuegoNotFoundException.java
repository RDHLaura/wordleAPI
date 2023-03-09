package com.example.wordle.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JuegoNotFoundException extends RuntimeException {
    public JuegoNotFoundException(){
        super("No se han encontrado datos de ningún juego");
    }
    public JuegoNotFoundException(Long id){
        super("No se ha encontrado el juego con el id " + id);
    }

}
