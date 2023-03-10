package com.example.wordle.controller;

import com.example.wordle.modelo.Palabra;
import com.example.wordle.service.PalabraService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PalabraController {
    private PalabraService palabraService;

    /**
     *
     * @param cantidadPalabras
     * @param longitudPalabra
     * @param startWith
     * @param endWith
     * @param candenaDentroPalabra
     * @return 404 si no encuentra palabras o 200 y la lista de palabras que coinciden los filtros especificados
     * @throws Exception
     */
    @GetMapping("/palabra")
    public ResponseEntity<?> getAllPalabras(
            @RequestParam(required = false, name="cantidadPalabras") Long cantidadPalabras,
            @RequestParam(required = false, name="longitudPalabra") Long longitudPalabra,
            @RequestParam(required = false, name="startWith") String startWith,
            @RequestParam(required = false, name="endWith") String endWith,
            @RequestParam(required = false, name="cadena") String candenaDentroPalabra
    ) throws Exception {
        List<Palabra> listaPalabras= palabraService.filtrarPalabras(cantidadPalabras,
                longitudPalabra, startWith, endWith, candenaDentroPalabra);

        if(listaPalabras.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(listaPalabras);
        }
    }
}
