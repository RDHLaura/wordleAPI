package com.example.wordle.service;

import com.example.wordle.dto.CreatePartidaDTO;
import com.example.wordle.error.JuegoNotFoundException;
import com.example.wordle.error.JugadorNotFoundException;
import com.example.wordle.modelo.Dificultad;
import com.example.wordle.modelo.Juego;
import com.example.wordle.modelo.Jugador;
import com.example.wordle.modelo.Partida;
import com.example.wordle.repo.JuegoRepo;
import com.example.wordle.repo.JugadorRepo;
import com.example.wordle.repo.PartidaRepo;
import com.example.wordle.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PartidaService extends BaseService<Partida, Long, PartidaRepo> {
    private final PartidaRepo partidaRepo;
    private final JugadorRepo jugadorRepo;
    private final JuegoRepo juegoRepo;

    /**
     * Crea una nueva partida
     * @param nueva datos de la nueva partida
     * @return notFoundException si no existe jugador o juego con esos ids o la nueva partida creada
     */
    public Partida nuevaPartida (CreatePartidaDTO nueva) {
        Jugador jugador = jugadorRepo.findById(nueva.getIdJugador()).orElse(null);
        if(jugador == null)
            throw new JugadorNotFoundException(nueva.getIdJugador());
        Juego juego = juegoRepo.findById(nueva.getIdJuego()).orElse(null);
        if(juego == null)
            throw new JuegoNotFoundException(nueva.getIdJuego());

        Partida partida = new Partida();

        partida.setJugador(jugador);
        partida.setJuego(juego);
        partida.setIntentos(nueva.getIntentos());
        partida.setPalabra(nueva.getPalabra());
        partida.setPuntos(calcularPuntos(nueva.getPalabra(), juego, nueva.getIntentos()));

        

        return save(partida);
    }

    /**
     * Calcula los puntos obtenidos en la partida
     * @param palabra
     * @param juego
     * @param intentos intentos utilizados
     * @return puntos obtenidos
     */
    private int calcularPuntos(String palabra, Juego juego, int intentos){

        int letrasDif = letrasDiferentes(palabra);

        int multiDificultad = multiplicadorDificultad(juego.getDificultad());
        double multiIntentosRestantes = 1 + ((juego.getIntentosMax() - intentos)* 0.1);

        double puntos = letrasDif * multiDificultad * multiIntentosRestantes;
        return (int)puntos;
    }

    /**
     * Calcula cuantas letras diferentes hay en una palabra
     * @param palabra
     * @return numCaracteresDiferentes int
     */
    private int letrasDiferentes(String palabra){

        Set<Character> set = new HashSet<>();
        for (char c : palabra.toCharArray()) {
            set.add(c);
        }
        int numCaracteresDiferentes = set.size();
        System.out.println(numCaracteresDiferentes);
        return numCaracteresDiferentes;
    }

    /**
     * Devuelve el multiplicador a utilizar según la dificultad del juego
     * @param dificultad
     * @return multiplicador
     */
    private int multiplicadorDificultad(Dificultad dificultad){
        int multiplicador = 0;
        switch (dificultad){
            case FACIL:
                multiplicador = 1;
                break;
            case NORMAL:
                multiplicador =  2;
                break;
            case DIFICIL:
                multiplicador =  3;
                break;
        }
        return multiplicador;
    }
}
