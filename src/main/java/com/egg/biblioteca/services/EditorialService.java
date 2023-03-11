package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.exceptions.MiException;

import java.util.List;

public interface EditorialService {

    void crearEditorial(String nombre) throws MiException;

    List<Editorial> listarEditoriales();

    void modificarEditorial(String nombre, String id) throws MiException;

    Editorial getOne(String id);

    void validar(String nombre, String id) throws MiException;

    void validar(String nombre) throws MiException;
}
