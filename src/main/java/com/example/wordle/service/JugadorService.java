package com.example.wordle.service;

import com.example.wordle.modelo.Jugador;
import com.example.wordle.repo.JugadorRepo;
import com.example.wordle.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JugadorService extends BaseService <Jugador, Long, JugadorRepo> {

}
