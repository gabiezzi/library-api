/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.repositories.EditorialRepository;
import com.egg.biblioteca.exceptions.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.egg.biblioteca.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* * @author Gabiezzi
 */
@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        validar(nombre);

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepository.save(editorial);
    }

    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepository.findAll();

        return editoriales;
    }

    @Transactional
    public void modificarEditorial(String nombre, String id) throws MiException {

        validar(nombre, id);

        Optional<Editorial> respuestaEditorial = editorialRepository.findById(id);

        if (respuestaEditorial.isPresent()) {

            Editorial editorial = respuestaEditorial.get();

            editorial.setNombre(nombre);

            editorialRepository.save(editorial);

        }
    }

    public Editorial getOne(String id){
        return editorialRepository.getOne(id);
    }
    public void validar(String nombre, String id) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre de la editorial no puede ser nulo o estar vacío.");
        }
        if (id.isEmpty() || id == null) {
            throw new MiException("El id de la editorial no puede ser nulo o estar vacío.");
        }

    }

    public void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre de la editorial no puede ser nulo o estar vacío.");
        }

    }
}
