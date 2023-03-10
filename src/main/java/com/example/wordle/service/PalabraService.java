package com.example.wordle.service;

import com.example.wordle.modelo.Palabra;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service
public class PalabraService {
    final static File diccionarioFile = new File("src/main/resources/palabras.txt");
    public List<Palabra> cargarDiccionario () throws FileNotFoundException, IOException {
        List<Palabra> diccionario = new ArrayList<>();
        Scanner myReader = new Scanner(diccionarioFile);
        int sum = 0;
        //lee las líneas del input, si es un salto de línea añade la suma a la lista
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            Palabra palabra = new Palabra(line.trim());
            diccionario.add(palabra);
        }
        myReader.close();
        return diccionario;
    }

    public List<Palabra> filtrarPalabras (Long numPalabras,
                                             Long longitudPalabra,
                                             String comienzoPalabra,
                                             String finalPalabra,
                                             String candenaDentroPalabra ) throws IOException, FileNotFoundException{

        List<Palabra> diccionario;
        //si se ha especificado una longitud de palabra se aplica el filtro
        if(longitudPalabra != null)
            diccionario = filtroLongitud(longitudPalabra);
        else
            diccionario = cargarDiccionario();

        //Aplico filtros del contenido de la palabra
        List<Palabra> diccionarioFiltrado = diccionario;

        //si se especifica el comienzo de la palabra se aplica el filtro
        if (comienzoPalabra != null)
            diccionarioFiltrado = filtroComienzo(comienzoPalabra, diccionarioFiltrado);
        if (finalPalabra != null)
            diccionarioFiltrado = filtroFinal(finalPalabra, diccionarioFiltrado);
        if (candenaDentroPalabra != null)
            diccionarioFiltrado = filtroContenido(candenaDentroPalabra, diccionarioFiltrado);


        Collections.shuffle(diccionarioFiltrado); // Mezcla la lista
        if(numPalabras==null)
            return diccionarioFiltrado;

        // Crea una sublista con el número de palabras indicado
        //si hay menos palabras que la solicitadas devolverá la lista completa
        if( numPalabras.intValue() > diccionarioFiltrado.size() )
            return diccionarioFiltrado;

        List<Palabra> resultado = diccionarioFiltrado.subList(0,  numPalabras.intValue());
        return resultado;
    }

    public List<Palabra> filtroLongitud (Long longitudPalabra) throws IOException, FileNotFoundException{

        List<Palabra> diccionario = cargarDiccionario();

        List<Palabra> diccFiltradorLongitud = new ArrayList<>();

        for (Palabra palabra : diccionario) {
            // Comprueba si la palabra empieza por la cadena especificada
            if (palabra.getPalabra().length() == longitudPalabra) {
                diccFiltradorLongitud.add(palabra);
            }
        }

        return diccFiltradorLongitud;
    }
    public List<Palabra> filtroComienzo (String comienzoPalabra, List<Palabra> diccionario) throws IOException, FileNotFoundException{

        List<Palabra> resultado = new ArrayList<>();

        for (Palabra palabra : diccionario) {
            // Comprueba si la palabra empieza por la cadena especificada
            if (palabra.getPalabra().startsWith(comienzoPalabra)) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }
    public List<Palabra> filtroFinal(String finalPalabra, List<Palabra> diccionario) throws IOException, FileNotFoundException{


        List<Palabra> resultado = new ArrayList<>();

        for (Palabra palabra : diccionario) {
            // Comprueba si la palabra empieza por la cadena especificada
            if (palabra.getPalabra().endsWith(finalPalabra)) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }
    public List<Palabra> filtroContenido(String cadena, List<Palabra> diccionario) throws IOException, FileNotFoundException{

        List<Palabra> resultado = new ArrayList<>();

        for (Palabra palabra : diccionario) {
            // Comprueba si la palabra empieza por la cadena especificada
            if (palabra.getPalabra().contains(cadena)) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }
}
