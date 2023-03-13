package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.exceptions.MiException;

import java.util.List;

public interface AutorService {

    Autor crearAutor(Autor autor) throws MiException;

    List<Autor> listarAutores();

    Autor modificarAutor(Autor autor, String id) throws MiException;

    Autor getOne(String id);

    void borrarAutor(String id);

}
