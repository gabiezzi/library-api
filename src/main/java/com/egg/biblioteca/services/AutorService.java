/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.repositories.AutorRepository;
import com.egg.biblioteca.exceptions.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    
   @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validar(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepository.save(autor);
    }

    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList();

        autores = autorRepository.findAll();

        return autores;

    }

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

    public Autor getOne(String id){
        return autorRepository.getOne(id);
    }

    private void validar(String nombre, String id) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del autor  no puede ser nulo o estar vacío.");
        }
        if (id.isEmpty() || id == null) {
            throw new MiException("El id del autor  no puede ser nulo o estar vacío.");
        }

    }



    private void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del autor no puede ser nulo o estar vacío.");
        }

    }
}
