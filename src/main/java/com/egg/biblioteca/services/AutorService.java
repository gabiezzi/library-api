package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.exceptions.MiException;

import java.util.List;

public interface AutorService {

    void crearAutor(String nombre) throws MiException;

    List<Autor> listarAutores();

    void modificarAutor(String nombre, String id) throws MiException;

    Autor getOne(String id);

    void validar(String nombre, String id) throws MiException;

    void validar(String nombre) throws MiException;
}
