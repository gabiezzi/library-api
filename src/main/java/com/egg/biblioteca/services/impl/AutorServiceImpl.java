/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.repositories.AutorRepository;
import com.egg.biblioteca.exceptions.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.egg.biblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl implements AutorService {
    
   @Autowired
    private AutorRepository autorRepository;

   @Override
    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validar(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepository.save(autor);
    }

    @Override
    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList();

        autores = autorRepository.findAll();

        return autores;

    }

    @Override
    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException {

        validar(nombre, id);

        Optional<Autor> respuestaAutor = autorRepository.findById(id);

        if (respuestaAutor.isPresent()) {

            Autor autor = respuestaAutor.get();

            autor.setNombre(nombre);

            autorRepository.save(autor);

        }
    }

    @Override
    public Autor getOne(String id){
        return autorRepository.getOne(id);
    }

    @Override
    public void validar(String nombre, String id) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del autor  no puede ser nulo o estar vacío.");
        }
        if (id.isEmpty() || id == null) {
            throw new MiException("El id del autor  no puede ser nulo o estar vacío.");
        }

    }


    @Override
    public void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del autor no puede ser nulo o estar vacío.");
        }

    }
}
